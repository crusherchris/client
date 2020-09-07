package me.zeroeightsix.kami.module.modules.render

import me.zeroeightsix.kami.module.Module
import net.minecraft.entity.Entity
import net.minecraft.util.EnumFacing

@Module.Info(
        name = "FRewrite",
        description = "uwu",
        category = Module.Category.RENDER
)
class FreecamRewrite : Module() {
    var e: Entity? = null
    var x = 0.0
    var y = 0.0
    var z = 0.0

    override fun onEnable() {
        x = mc.player.posX
        y = mc.player.posY
        z = mc.player.posZ

        e = mc.renderViewEntity
        e?.entityId = -1000

        mc.renderViewEntity = e
    }

    override fun onUpdate() {
        updatePos()
        cancelMovement()
        mc.renderViewEntity?.posX = x
        mc.renderViewEntity?.posY = y
        mc.renderViewEntity?.posZ = z
    }

    private fun updatePos() {
        val mi = mc.player.movementInput
        if (mi.forwardKeyDown) {
            lookToDirection(1)
        } else if (mi.backKeyDown) {
            lookToDirection(-1)
        }
    }

    private fun cancelMovement() {
        mc.player.movementInput.moveForward = 0f
        mc.player.movementInput.moveStrafe = 0f
        mc.player.movementInput.jump = false
        mc.player.movementInput.sneak = false
    }

    private fun lookToDirection(multiply: Int) {
        return when (mc.player.horizontalFacing) {
            EnumFacing.NORTH -> z += -1.0 * multiply
            EnumFacing.SOUTH -> z += 1.0 * multiply
            EnumFacing.WEST -> x += -1.0 * multiply
            EnumFacing.EAST -> x += 1.0 * multiply
            else -> {}
        }
    }

    override fun onDisable() {
        mc.renderViewEntity = mc.player
    }
}