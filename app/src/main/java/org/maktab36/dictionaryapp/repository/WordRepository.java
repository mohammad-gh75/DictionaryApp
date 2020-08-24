package org.maktab36.dictionaryapp.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.maktab36.dictionaryapp.database.DataBaseHelper;
import org.maktab36.dictionaryapp.database.DictionaryDBSchema.*;
import org.maktab36.dictionaryapp.database.cursorwrapper.WordCursorWrapper;
import org.maktab36.dictionaryapp.model.Word;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class WordRepository {
    private static WordRepository sWordRepository;
    private static Context mContext;
    private SQLiteDatabase mDatabase;

    public static WordRepository getInstance(Context context) {
        mContext = context.getApplicationContext();
        if (sWordRepository == null) {
            sWordRepository = new WordRepository();
        }
        return sWordRepository;
    }

    private WordRepository() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(mContext);
        mDatabase = dataBaseHelper.getWritableDatabase();
    }

    public List<Word> getWords() {
        List<Word> words = new ArrayList<>();
        WordCursorWrapper wordCursorWrapper = queryWords(null, null);
        try {
            wordCursorWrapper.moveToFirst();
            while (!wordCursorWrapper.isAfterLast()) {
                words.add(wordCursorWrapper.getWord());
                wordCursorWrapper.moveToNext();
            }
        } finally {
            wordCursorWrapper.close();
        }
        return words;
    }


    public Word get(UUID uuid) {
        String selection= WordTable.COLS.UUID + "=?";
        String[] selectionArgs= new String[]{uuid.toString()};;
        WordCursorWrapper wordCursorWrapper = queryWords(selection, selectionArgs);
        try {
            wordCursorWrapper.moveToFirst();
            return wordCursorWrapper.getWord();
        } finally {
            wordCursorWrapper.close();
        }
    }

    public void update(Word word) {
        ContentValues values = getWordContentValue(word);
        String where = WordTable.COLS.UUID + "=?";
        String[] whereArgs = new String[]{word.getId().toString()};
        mDatabase.update(WordTable.NAME, values, where, whereArgs);
    }

    public void delete(Word word) {
        String selection = WordTable.COLS.UUID + "=?";
        String[] selectionArgs = new String[]{word.getId().toString()};
        mDatabase.delete(WordTable.NAME, selection, selectionArgs);
    }


    public void insert(Word word) {
        //TODO: check if not exist.
        ContentValues values = getWordContentValue(word);
        mDatabase.insert(WordTable.NAME, null, values);
    }

    public void deleteAll() {
        //TODO: implement deleteAll method
        /*String selection;
        String[] selectionArgs;
        if (userId.equals(mAdminId)) {
            selection = null;
            selectionArgs = null;
        } else {
            selection = TaskTable.COLS.USER_ID + "=?";
            selectionArgs = new String[]{userId.toString()};
        }
        mDatabase.delete(TaskTable.NAME, selection, selectionArgs);*/
    }

    public List<Word> searchWords(String english, String persian, String arabic, String french) {
        //TODO: implement search method.
        /*List<Task> searchedTasks = new ArrayList<>();
        StringBuilder selectBuilder = new StringBuilder();
        List<String> selectionList = new ArrayList<>();
        if (!name.equals("")) {
            if (selectBuilder.length() != 0) {
                selectBuilder.append(" AND ");
            }
            selectBuilder.append(TaskTable.COLS.NAME + " LIKE ?");
            selectionList.add("%"+name+"%");
        }
        if (!description.equals("")) {
            if (selectBuilder.length() != 0) {
                selectBuilder.append(" AND ");
            }
            selectBuilder.append(TaskTable.COLS.DESCRIPTION + " LIKE ?");
            selectionList.add("%"+description+"%");
        }
        if (dateFrom!=null) {
            if (selectBuilder.length() != 0) {
                selectBuilder.append(" AND ");
            }
            selectBuilder.append(TaskTable.COLS.DATE + ">=?");
            selectionList.add(String.valueOf(dateFrom.getTime()));
        }
        if (dateTo!=null) {
            if (selectBuilder.length() != 0) {
                selectBuilder.append(" AND ");
            }
            selectBuilder.append(TaskTable.COLS.DATE + "<=?");
            selectionList.add(String.valueOf(dateTo.getTime()));
        }
        if (!userId.equals(mAdminId)) {
            if (selectBuilder.length() != 0) {
                selectBuilder.append(" AND ");
            }
            selectBuilder.append(TaskTable.COLS.USER_ID + "=?");
            selectionList.add(userId.toString());
        }
        String selection = selectBuilder.toString();
        String[] selectionArgs=new String[selectionList.size()];
        selectionList.toArray(selectionArgs);
        TaskCursorWrapper taskCursorWrapper = queryWords(selection, selectionArgs);
        try {
            taskCursorWrapper.moveToFirst();
            while (!taskCursorWrapper.isAfterLast()) {
                searchedTasks.add(taskCursorWrapper.getTask());
                taskCursorWrapper.moveToNext();
            }
        } finally {
            taskCursorWrapper.close();
        }
        return searchedTasks;*/

        return null;
    }

    public int getNumberOfWords() {
        WordCursorWrapper wordCursorWrapper = queryWords(null, null);
        try {
            wordCursorWrapper.moveToFirst();
            return wordCursorWrapper.getCount();
        } finally {
            wordCursorWrapper.close();
        }
    }

    private WordCursorWrapper queryWords(String selection, String[] selectionArgs) {
        Cursor cursor = mDatabase.query(WordTable.NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null);

        return new WordCursorWrapper(cursor);
    }

    private ContentValues getWordContentValue(Word word) {
        ContentValues values = new ContentValues();
        values.put(WordTable.COLS.UUID, word.getId().toString());
        values.put(WordTable.COLS.ENGLISH, word.getEnglish());
        values.put(WordTable.COLS.PERSIAN, word.getPersian());
        values.put(WordTable.COLS.ARABIC, word.getArabic());
        values.put(WordTable.COLS.FRENCH, word.getFrench());
        return values;
    }
}
