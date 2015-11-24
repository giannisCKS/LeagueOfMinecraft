package com.bocktrow.lom.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;

@SuppressWarnings("deprecation")
public class Attributes {
    public enum Operation {
        ADD_NUMBER(0),
        MULTIPLY_PERCENTAGE(1),
        ADD_PERCENTAGE(2);
        private int id;

        private Operation(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public static Operation fromId(int id) {
            // Linear scan is very fast for small N
            for (Operation op : values()) {
                if (op.getId() == id) {
                    return op;
                }
            }
            throw new IllegalArgumentException("Corrupt operation ID " + id + " detected.");
        }
    }

    public static class AttributeType {
        private static ConcurrentMap<String, AttributeType> LOOKUP = Maps.newConcurrentMap();
        public static final AttributeType GENERIC_MAX_HEALTH = new AttributeType("generic.maxHealth").register();
        public static final AttributeType GENERIC_FOLLOW_RANGE = new AttributeType("generic.followRange").register();
        public static final AttributeType GENERIC_ATTACK_DAMAGE = new AttributeType("generic.attackDamage").register();
        public static final AttributeType GENERIC_MOVEMENT_SPEED = new AttributeType("generic.movementSpeed").register();
        public static final AttributeType GENERIC_KNOCKBACK_RESISTANCE = new AttributeType("generic.knockbackResistance").register();

        private final String minecraftId;

        /**
         * Construct a new attribute type.
         * <p>
         * Remember to {@link #register()} the type.
         * @param minecraftId - the ID of the type.
         */
        public AttributeType(String minecraftId) {
            this.minecraftId = minecraftId;
        }

        /**
         * Retrieve the associated minecraft ID.
         * @return The associated ID.
         */
        public String getMinecraftId() {
            return minecraftId;
        }

        /**
         * Register the type in the central registry.
         * @return The registered type.
         */
        // Constructors should have no side-effects!
        public AttributeType register() {
            AttributeType old = LOOKUP.putIfAbsent(minecraftId, this);
            return old != null ? old : this;
        }

        /**
         * Retrieve the attribute type associated with a given ID.
         * @param minecraftId The ID to search for.
         * @return The attribute type, or NULL if not found.
         */
        public static AttributeType fromId(String minecraftId) {
            return LOOKUP.get(minecraftId);
        }

        /**
         * Retrieve every registered attribute type.
         * @return Every type.
         */
        public static Iterable<AttributeType> values() {
            return LOOKUP.values();
        }
    }

    public static class Attribute {
        private NBTTagCompound data;

        private Attribute(Builder builder) {
            data = new NBTTagCompound();
            setAmount(builder.amount);
            setOperation(builder.operation);
            setAttributeType(builder.type);
            setName(builder.name);
            setUUID(builder.uuid);
        }

        private Attribute(NBTTagCompound data) {
            this.data = data;
        }

        public double getAmount() {
            return data.hasKey("Amount") ? data.getDouble("Amount") : 0;
        }

        public void setAmount(double amount) {
            data.setDouble("Amount", amount);
        }

        public Operation getOperation() {
            return Operation.fromId(data.hasKey("Operation") ? data.getInt("Operation") : 0);
        }

        public void setOperation(@Nonnull Operation operation) {
            Preconditions.checkNotNull(operation, "operation cannot be NULL.");
            data.setInt("Operation", operation.getId());
        }

        public AttributeType getAttributeType() {
            return AttributeType.fromId(data.hasKey("AttributeName") ? data.getString("AttributeName") : null);
        }

        public void setAttributeType(@Nonnull AttributeType type) {
            Preconditions.checkNotNull(type, "type cannot be NULL.");
            data.setString("AttributeName", type.getMinecraftId());
        }

        public String getName() {
            return data.hasKey("Name") ? data.getString("Name") : null;
        }

        public void setName(@Nonnull String name) {
            Preconditions.checkNotNull(name, "name cannot be NULL.");
            data.setString("Name", name);
        }

        public UUID getUUID() {
            return new UUID(data.getLong("UUIDMost"),data.getLong("UUIDLeast"));
        }

        public void setUUID(@Nonnull UUID id) {
            Preconditions.checkNotNull("id", "id cannot be NULL.");
            data.setLong("UUIDLeast", id.getLeastSignificantBits());
            data.setLong("UUIDMost", id.getMostSignificantBits());
        }

        /**
         * Construct a new attribute builder with a random UUID and default operation of adding numbers.
         * @return The attribute builder.
         */
        public static Builder newBuilder() {
            return new Builder().uuid(UUID.randomUUID()).operation(Operation.ADD_NUMBER);
        }

        // Makes it easier to construct an attribute
        public static class Builder {
            private double amount;
            private Operation operation = Operation.ADD_NUMBER;
            private AttributeType type;
            private String name;
            private UUID uuid;

            private Builder() {
                // Don't make this accessible
            }

            public Builder amount(double amount) {
                this.amount = amount;
                return this;
            }
            public Builder operation(Operation operation) {
                this.operation = operation;
                return this;
            }
            public Builder type(AttributeType type) {
                this.type = type;
                return this;
            }
            public Builder name(String name) {
                this.name = name;
                return this;
            }
            public Builder uuid(UUID uuid) {
                this.uuid = uuid;
                return this;
            }
            public Attribute build() {
                return new Attribute(this);
            }
        }
    }

    // This may be modified
    public ItemStack stack;
    private NBTTagList attributes;

    public Attributes(ItemStack stack) {
        // Create a CraftItemStack (under the hood)
        this.stack = getCraftItemStack(stack);
    }

    public static ItemStack getCraftItemStack(ItemStack stack) {
        // Any need to convert?
        if (stack == null)
            return stack;
        try {
            // Call the private constructor
            Constructor<?> caller = CraftItemStack.class.getDeclaredConstructor(ItemStack.class);
            caller.setAccessible(true);
            return (ItemStack) caller.newInstance(stack);
        } catch (Exception e) {
            throw new IllegalStateException("Unable to convert " + stack + " + to a CraftItemStack.");
        }
    }

    private static Object getFieldValue(Field field, Object target) {
        try {
            return field.get(target);
        } catch (Exception e) {
            throw new RuntimeException("Unable to retrieve " + field + " for " + target, e);
        }
    }

    private static void checkItemStack(ItemStack stack) {
        if (stack == null)
            throw new IllegalArgumentException("Stack cannot be NULL.");
        if (CraftItemStack.class.isAssignableFrom(stack.getClass()))
            throw new IllegalArgumentException("Stack must be a CraftItemStack.");
        if (stack.getType() == Material.AIR)
            throw new IllegalArgumentException("ItemStacks representing air cannot store NMS information.");
    }

    /**
     * Retrieve the modified item stack.
     * @return The modified item stack.
     */
    public ItemStack getStack() {
        return stack;
    }

    /**
     * Retrieve the number of attributes.
     * @return Number of attributes.
     */
    public int size() {
        return attributes.size();
    }

    /**
     * Add a new attribute to the list.
     * @param attribute - the new attribute.
     */
    public void add(Attribute attribute) {
        Preconditions.checkNotNull(attribute.getName(), "must specify an attribute name.");
        attributes.add(attribute.data);
    }


    public void clear() {
        attributes.f();
    }

}