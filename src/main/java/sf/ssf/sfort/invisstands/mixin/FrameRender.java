package sf.ssf.sfort.invisstands.mixin;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(LivingEntityRenderer.class)
public abstract class FrameRender<T extends LivingEntity> {
	@ModifyArgs(method = "render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;getRenderLayer(Lnet/minecraft/entity/LivingEntity;ZZZ)Lnet/minecraft/client/render/RenderLayer;"))
	public void getRenderLayer(Args args) {
		LivingEntity entity = args.get(0);
		if (entity instanceof ArmorStandEntity)
		if (!ssf$hasItems((ArmorStandEntity) entity))
			args.set(1, true);
		else if (args.get(1).equals(false))
			args.set(2, false);
	}
	private boolean ssf$hasItems(ArmorStandEntity entity) {
		for(ItemStack item : entity.getArmorItems())
			if(item != ItemStack.EMPTY)
				return true;
		return false;
	}
}
