package WebInMemoryDB.WebInMemoryDB.DAO;

import DB.CommandGenerators.CommandsGenerator;
import DB.Commands.Command;
import DB.Database;
import org.springframework.stereotype.Component;

@Component
public class DatabaseDAO {
    private final CommandsGenerator commandsGenerator = CommandsGenerator.getCommandGenerator();
    private final Database database = Database.getDatabase();

    public String executeSQLCommand(String query) {
        try {
            Command command = commandsGenerator.generateFromSqlQuery(query);
            return database.execute(command);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
