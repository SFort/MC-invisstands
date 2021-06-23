package sf.ssf.sfort.invisstands.mixin;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.ArmorStandEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.ArmorStandArmorEntityModel;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ArmorStandEntityRenderer.class)
public abstract class FrameRender extends LivingEntityRenderer<ArmorStandEntity, ArmorStandArmorEntityModel> {
	public FrameRender(EntityRendererFactory.Context ctx, ArmorStandArmorEntityModel model, float shadowRadius) {
		super(ctx, model, shadowRadius);
	}
	@Nullable
	public RenderLayer getRenderLayer(ArmorStandEntity entity, boolean showBody, boolean translucent, boolean showOutline) {
		if (!ssf$hasItems(entity))
			showBody = true;
		else if (!showBody)
			translucent = false;

		return super.getRenderLayer(entity, showBody, translucent, showOutline);
	}
	private boolean ssf$hasItems(ArmorStandEntity entity) {
		for(ItemStack item : entity.getArmorItems())
			if(item != ItemStack.EMPTY)
				return true;
		return false;
	}
}
