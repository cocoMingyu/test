package design_pattern.command.service;

import design_pattern.command.entity.Light;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/5/20 13:59
 * @ Modified By:
 */
public class LightOff implements Command {
    private Light light;

    public LightOff(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}
