package design_pattern.command.service;

import design_pattern.command.entity.Computer;

/**
 * @ Author:kn
 * @ Description:
 * @ Date:Created in 2019/5/20 13:56
 * @ Modified By:
 */
public class ComputerOn implements Command {
    private Computer computer;

    public ComputerOn(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void execute() {
        computer.on();
    }

    @Override
    public void undo() {
        computer.off();
    }
}
