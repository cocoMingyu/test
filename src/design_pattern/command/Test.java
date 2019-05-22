package design_pattern.command;

import design_pattern.command.entity.Computer;
import design_pattern.command.entity.Door;
import design_pattern.command.entity.Light;
import design_pattern.command.service.*;

/**
 * @ Author:kn
 * @ Description:命令模式：定义：将“请求”封装成对象，以便使用不同的请求、队列或者日志来参数化其他对象。命令模式也支持可撤销的操作。
 * 命令模式就是把命令封装成对象，然后将动作请求者与动作执行者完全解耦，上例中遥控器的按钮和电器一毛钱关系都没吧。
 * 还记得定义中提到了队列，命令模式如何用于队列呢，比如饭店有很多个点菜的地方，有一个做菜的地方，把点菜看作命令，做菜看作命令执行者，不断有人点菜就相当于把菜加入队列，对于做菜的只管从队列里面取，取一个做一个。
 * 定义中还提到了日志，日志一般用于记录用户行为，或者在异常时恢复时用的，比如每个命令现在包含两个方法，一个执行execute，一个undo，
 * 我们可以把用户所有命令调用保存到日志中，比如用户操作不当了，电器异常了，只需要把日志中所有的命令拿出来执行一遍undo就完全恢复了，是吧，就是这么个意思。
 */
public class Test {
    public static void main(String[] args) {
        Computer computer = new Computer();
        Door door = new Door();
        Light light = new Light();
        ControlPanel panel = new ControlPanel();
        panel.setKey(1,new ComputerOn(computer));
        panel.setKey(2,new ComputerOff(computer));
        QuickCommand quickCommand = new QuickCommand(new Command[]{new DoorOff(door),new LightOff(light),new ComputerOn(computer)});
        panel.setKey(8,quickCommand);

        //panel.executeKey(1);
        panel.executeKey(8);
        panel.executeKey(1);
        panel.undo();

    }
}
