package org.maktab36.dictionaryapp.database.cursorwrapper;

import android.database.Cursor;
import android.database.CursorWrapper;

import org.maktab36.dictionaryapp.database.DictionaryDBSchema.*;
import org.maktab36.dictionaryapp.model.Word;

import java.util.UUID;


public class WordCursorWrapper extends CursorWrapper {
    public WordCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Word getWord(){
        String stringUUID=getString(getColumnIndex(WordTable.COLS.UUID));
        String english=getString(getColumnIndex(WordTable.COLS.ENGLISH));
        String persian=getString(getColumnIndex(WordTable.COLS.PERSIAN));
        String arabic=getString(getColumnIndex(WordTable.COLS.ARABIC));
        String french=getString(getColumnIndex(WordTable.COLS.FRENCH));

        Word word=new Word(UUID.fromString(stringUUID),
                english,
                persian,
                arabic,
                french);
        return word;
    }

}
