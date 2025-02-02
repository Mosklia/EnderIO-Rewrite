package com.enderio.base.common.capability.fluid;

import net.minecraft.tags.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

/**
 * FluidHandler for Items which only accept specific fluids
 */
public class AcceptingFluidItemHandler extends FluidHandlerItemStack {

    private final Predicate<Fluid> fluidPredicate;

    public AcceptingFluidItemHandler(@NotNull ItemStack container, int capacity, Fluid validFluid) {
        this(container, capacity, fluid -> fluid == validFluid);
    }
    public AcceptingFluidItemHandler(@NotNull ItemStack container, int capacity, Tag<Fluid> validFluid) {
        this(container, capacity, validFluid::contains);
    }

    public AcceptingFluidItemHandler(@NotNull ItemStack container, int capacity, Predicate<Fluid> isFluidValid) {
        super(container, capacity);
        fluidPredicate = isFluidValid;
    }

    @Override
    public boolean canFillFluidType(FluidStack fluid) {
        return fluidPredicate.test(fluid.getFluid());
    }

    @Override
    public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
        return fluidPredicate.test(stack.getFluid());
    }
}
