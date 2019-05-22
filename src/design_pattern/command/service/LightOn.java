package design_pattern.command.service;

import design_pattern.command.entity.Light;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/5/20 13:58
 * @ Modified By:
 */
public class LightOn implements Command{
    private Light light;

    public LightOn(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}
