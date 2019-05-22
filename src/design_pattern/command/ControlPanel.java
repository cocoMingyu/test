package design_pattern.command;

import design_pattern.command.service.Command;
import design_pattern.command.service.NoCommand;

/**
 * @ Author:kn
 * @ Description:控制器面板，一共有九个按钮
 * @ Date:Created in 2019/5/20 14:02
 * @ Modified By:
 */
public class ControlPanel {
    private static final int CONTROL_SIZE=9;
    private static int count=0;
    private Command[] commands;
    private Command lastCommand;//定义变量保存上一个命令，方便执行撤销


    //初始化命令数组大小，每个都为空命令
    public ControlPanel() {
        lastCommand=new NoCommand();
        commands=new Command[CONTROL_SIZE];
        for (int i = 0; i < commands.length; i++) {
            commands[i]=new NoCommand();
        }
    }

    //为按钮绑定命令
    public void setKey(int key,Command command){
        commands[key]=command;
    }

    //执行按键命令
    public void executeKey(int key){
        commands[key].execute();
        ++count;
        if (count%2!=0){
            lastCommand=commands[key];
        }
    }

    //执行撤销，执行完变为空命令
    public void undo(){
        System.out.println("-----------------执行撤销----------------");
        lastCommand.execute();
        lastCommand=new NoCommand();
    }
}
