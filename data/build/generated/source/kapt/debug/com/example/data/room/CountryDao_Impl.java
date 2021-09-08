package com.example.data.room;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.rxjava3.RxRoom;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.data.model.TableModel;
import io.reactivex.rxjava3.core.Flowable;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CountryDao_Impl implements CountryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TableModel> __insertionAdapterOfTableModel;

  public CountryDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTableModel = new EntityInsertionAdapter<TableModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Country` (`name`,`capital`,`area`,`flag`,`population`,`currentDistance`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TableModel value) {
        if (value.getName() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getName());
        }
        if (value.getCapital() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getCapital());
        }
        stmt.bindDouble(3, value.getArea());
        if (value.getFlag() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getFlag());
        }
        stmt.bindLong(5, value.getPopulation());
        stmt.bindLong(6, value.getCurrentDistance());
      }
    };
  }

  @Override
  public void insertDatabase(final List<TableModel> list) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTableModel.insert(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Flowable<List<TableModel>> getAllCountryDB() {
    final String _sql = "SELECT * FROM country";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, false, new String[]{"country"}, new Callable<List<TableModel>>() {
      @Override
      public List<TableModel> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCapital = CursorUtil.getColumnIndexOrThrow(_cursor, "capital");
          final int _cursorIndexOfArea = CursorUtil.getColumnIndexOrThrow(_cursor, "area");
          final int _cursorIndexOfFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "flag");
          final int _cursorIndexOfPopulation = CursorUtil.getColumnIndexOrThrow(_cursor, "population");
          final int _cursorIndexOfCurrentDistance = CursorUtil.getColumnIndexOrThrow(_cursor, "currentDistance");
          final List<TableModel> _result = new ArrayList<TableModel>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final TableModel _item;
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpCapital;
            if (_cursor.isNull(_cursorIndexOfCapital)) {
              _tmpCapital = null;
            } else {
              _tmpCapital = _cursor.getString(_cursorIndexOfCapital);
            }
            final float _tmpArea;
            _tmpArea = _cursor.getFloat(_cursorIndexOfArea);
            final String _tmpFlag;
            if (_cursor.isNull(_cursorIndexOfFlag)) {
              _tmpFlag = null;
            } else {
              _tmpFlag = _cursor.getString(_cursorIndexOfFlag);
            }
            final int _tmpPopulation;
            _tmpPopulation = _cursor.getInt(_cursorIndexOfPopulation);
            final int _tmpCurrentDistance;
            _tmpCurrentDistance = _cursor.getInt(_cursorIndexOfCurrentDistance);
            _item = new TableModel(_tmpName,_tmpCapital,_tmpArea,_tmpFlag,_tmpPopulation,_tmpCurrentDistance);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flowable<TableModel> getCountryByNameDB(final String name) {
    final String _sql = "SELECT * FROM country WHERE name IN (?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, name);
    }
    return RxRoom.createFlowable(__db, false, new String[]{"country"}, new Callable<TableModel>() {
      @Override
      public TableModel call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCapital = CursorUtil.getColumnIndexOrThrow(_cursor, "capital");
          final int _cursorIndexOfArea = CursorUtil.getColumnIndexOrThrow(_cursor, "area");
          final int _cursorIndexOfFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "flag");
          final int _cursorIndexOfPopulation = CursorUtil.getColumnIndexOrThrow(_cursor, "population");
          final int _cursorIndexOfCurrentDistance = CursorUtil.getColumnIndexOrThrow(_cursor, "currentDistance");
          final TableModel _result;
          if(_cursor.moveToFirst()) {
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpCapital;
            if (_cursor.isNull(_cursorIndexOfCapital)) {
              _tmpCapital = null;
            } else {
              _tmpCapital = _cursor.getString(_cursorIndexOfCapital);
            }
            final float _tmpArea;
            _tmpArea = _cursor.getFloat(_cursorIndexOfArea);
            final String _tmpFlag;
            if (_cursor.isNull(_cursorIndexOfFlag)) {
              _tmpFlag = null;
            } else {
              _tmpFlag = _cursor.getString(_cursorIndexOfFlag);
            }
            final int _tmpPopulation;
            _tmpPopulation = _cursor.getInt(_cursorIndexOfPopulation);
            final int _tmpCurrentDistance;
            _tmpCurrentDistance = _cursor.getInt(_cursorIndexOfCurrentDistance);
            _result = new TableModel(_tmpName,_tmpCapital,_tmpArea,_tmpFlag,_tmpPopulation,_tmpCurrentDistance);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
