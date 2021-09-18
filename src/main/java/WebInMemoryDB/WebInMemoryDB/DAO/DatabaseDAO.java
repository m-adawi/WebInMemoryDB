package WebInMemoryDB.WebInMemoryDB.DAO;

import DB.Attributes.IntegerDatabaseKey;
import DB.Attributes.StudentAttributeType;
import DB.Attributes.StudentID;
import DB.CommandGenerators.CommandsGenerator;
import DB.Commands.Command;
import DB.Commands.DeleteCommand;
import DB.Commands.InsertRecordCommand;
import DB.Commands.SelectCommand;
import DB.Conditions.IDEqualCondition;
import DB.Database;
import DB.Record;
import org.gibello.zql.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

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

    public Record generateRecord(HashMap<String, String> formData) {
        Record record = new Record(new StudentID(formData.get(StudentAttributeType.ID.name())));
        for (StudentAttributeType type : StudentAttributeType.values()) {
            if (type.equals(StudentAttributeType.ID))
                continue;
            String attributeStrVal = formData.get(type.name());
            if(attributeStrVal.equals(""))
                continue;
            record.setAttributeFromTypeAndStrValue(type, attributeStrVal);
        }
        return record;
    }

    public void insertRecord(Record record) {
        database.insertRecord(record);
    }

    public void deleteRecord(String id) {
        database.deleteRecordByKey(new IntegerDatabaseKey(id));
    }

    public boolean containsID(String id) {
        return database.getKeysCollection().containsKey(new IntegerDatabaseKey(id));
    }

    public Record selectRecord(String id) {
        return database.selectRecordByKey(new IntegerDatabaseKey(id));
    }

    public void updateRecord(String id, Record record) {
        database.updateRecordByKey(new IntegerDatabaseKey(id), record);
    }

    public String[][] executeQuery(String sql) throws ParseException {
        Command command = commandsGenerator.generateFromSqlQuery(sql);
        return database.executeQuery((SelectCommand) command);
    }
}
