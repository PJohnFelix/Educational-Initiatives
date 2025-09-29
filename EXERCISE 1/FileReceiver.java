// File: src/patterns/behavioral/command/FileReceiver.java
package patterns.behavioral.command;

public class FileReceiver {
    private String log;
    
    public String getLog() {
        return log;
    }

    public void renameFile(String oldName, String newName) {
        log = "Renamed file from " + oldName + " to " + newName;
    }

    public void deleteFile(String fileName) {
        log = "Deleted file: " + fileName;
    }

    public void undoRename(String newName, String oldName) {
        log = "UNDO: Renamed file from " + newName + " back to " + oldName;
    }

    public void undoDelete(String fileName) {
        log = "UNDO: Restored file: " + fileName;
    }
}

// File: src/patterns/behavioral/command/FileCommand.java
package patterns.behavioral.command;

public interface FileCommand {
    void execute();
    void undo();
}

// File: src/patterns/behavioral/command/RenameCommand.java
package patterns.behavioral.command;

public class RenameCommand implements FileCommand {
    private FileReceiver receiver;
    private String oldName;
    private String newName;
    
    public RenameCommand(FileReceiver receiver, String oldName, String newName) {
        this.receiver = receiver;
        this.oldName = oldName;
        this.newName = newName;
    }

    @Override
    public void execute() {
        receiver.renameFile(oldName, newName);
    }

    @Override
    public void undo() {
        receiver.undoRename(newName, oldName);
    }
}

// File: src/patterns/behavioral/command/DeleteCommand.java
package patterns.behavioral.command;

public class DeleteCommand implements FileCommand {
    private FileReceiver receiver;
    private String fileName;
    
    public DeleteCommand(FileReceiver receiver, String fileName) {
        this.receiver = receiver;
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        receiver.deleteFile(fileName);
    }

    @Override
    public void undo() {
        receiver.undoDelete(fileName);
    }
}

// File: src/patterns/behavioral/command/CommandInvoker.java
package patterns.behavioral.command;

import java.util.Stack;

public class CommandInvoker {
    private Stack<FileCommand> history = new Stack<>();

    public void executeCommand(FileCommand command) {
        command.execute();
        history.push(command);
    }

    public void undoLast() {
        if (!history.isEmpty()) {
            FileCommand command = history.pop();
            command.undo();
        }
    }
}