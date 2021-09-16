package WebInMemoryDB.WebInMemoryDB.DAO;

import DB.Attributes.IntegerDatabaseKey;
import DB.CommandGenerators.CommandsGenerator;
import DB.Commands.Command;
import DB.Commands.DeleteCommand;
import DB.Commands.InsertRecordCommand;
import DB.Conditions.IDEqualCondition;
import DB.Database;
import DB.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseDAO {
    @Autowired
    CommandsGenerator commandsGenerator;
    @Autowired
    Database database;

    public String executeSQLCommand(String query) {
        try {
            Command command = commandsGenerator.generateFromSqlQuery(query);
            return database.execute(command);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public void insertRecord(Record record) {
        Command command = new InsertRecordCommand(record);
        database.execute(command);
    }

    public void deleteRecord(String id) {
        Command command = new DeleteCommand(new IDEqualCondition(new IntegerDatabaseKey(id)));
        database.execute(command);
    }
}
