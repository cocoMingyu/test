package design_pattern.command.service;

import design_pattern.command.entity.Door;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/5/20 14:00
 * @ Modified By:
 */
public class DoorOn implements Command {
    private Door door;

    public DoorOn(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.on();
    }

    @Override
    public void undo() {
        door.off();
    }
}
