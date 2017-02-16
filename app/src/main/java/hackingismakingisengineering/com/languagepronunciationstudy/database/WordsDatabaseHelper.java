package hackingismakingisengineering.com.languagepronunciationstudy.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.provider.BaseColumns;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import hackingismakingisengineering.com.languagepronunciationstudy.R;
import hackingismakingisengineering.com.languagepronunciationstudy.model.Word;

/**
 * Created by helloworld on 12/2/17.
 */

public class WordsDatabaseHelper extends OrmLiteSqliteOpenHelper {



    // . db is required as the file extension of the SQLite file
    public static final String DB_NAME = "dictionary.db";

    public static final int DB_VERSION_NUMBER = 2;

    private Dao<Word, Long> wordDao;

    public WordsDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION_NUMBER, R.raw.ormlite_config);
        //super(context, DB_NAME, null, DB_VERSION_NUMBER, Integer.parseInt(null));

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {



        //Todo: refactor

        try {
            TableUtils.createTable(connectionSource, Word.class);
        }catch (SQLiteException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // this is called when doing a database migration
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try{

            //todo refactor
            TableUtils.dropTable(connectionSource, Word.class, false);
            onCreate(sqLiteDatabase, connectionSource);

        }catch (SQLiteException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Word, Long> getDao() throws SQLException{
        if (wordDao == null) {
            wordDao = getDao(Word.class);

        }
        return wordDao;
    }
}
