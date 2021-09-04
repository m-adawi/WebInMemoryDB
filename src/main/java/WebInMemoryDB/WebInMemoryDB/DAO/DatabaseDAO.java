package WebInMemoryDB.WebInMemoryDB.DAO;

import DB.CommandGenerators.CommandsGenerator;
import DB.Commands.Command;
import DB.Database;
import org.springframework.stereotype.Component;

@Component
public class DatabaseDAO {
    private CommandsGenerator commandsGenerator = CommandsGenerator.getCommandGenerator();
    private Database database = Database.getDatabase();

    public String executeDatabaseQuery(String query) {
        try {
            Command command = commandsGenerator.generateFromSqlQuery(query);
            return database.execute(command);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
