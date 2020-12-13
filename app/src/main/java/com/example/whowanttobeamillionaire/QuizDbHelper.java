package com.example.whowanttobeamillionaire;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class QuizDbHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    public static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER " + " ) ";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable()
    {
        Question q1 = new Question("Как оперативная память называется на английском?", "DDR", "ROM", "RAM", "PCI", 3);
        addQuestion(q1);
        Question q2 = new Question("Дизъюнкция это?", "Логическое сложение", "Логическое умножение", "Логическое деление", "Логическое вычитание", 1);
        addQuestion(q2);
        Question q3 = new Question("В какой системе счисления работают большинство компьютеров?", "Десятичной", "Шестнадцатиричной", "Восьмеричной", "Двоичной", 4);
        addQuestion(q3);
        Question q4 = new Question("Какой из этих портов для устройств самый распространенный?", "HDMI", "PS/2", "LPT", "USB", 4);
        addQuestion(q4);
        Question q5 = new Question("Кто впервые сформулировал три закона робототехники?", "Станислав Лем", "Айзек Азимов", "Рэй Брэдбери", "Герберт Уэллс", 2);
        addQuestion(q5);
        Question q6 = new Question("Какой браузер является самым популярным в мире?", "Google Chrome", "Internet Explorer", "Opera", "Safari", 1);
        addQuestion(q6);
        Question q7 = new Question("В каком штате США расположена Кремниевая долина?", "Флорида", "Техас", "Калифорния", "Оклахома", 3);
        addQuestion(q7);
        Question q8 = new Question("Какой архитектуры процессоров не существует?", "RISC", "CISC", "MISC", "LISK", 4);
        addQuestion(q8);
        Question q9 = new Question("Какая из перечисленных архитектур ЭВМ действительно существует?", "Кэмбриджская", "Бостонская", "Принстонская ", "Стэнфордская", 3);
        addQuestion(q9);
        Question q10 = new Question("Как называют IT-компании стоимость которых превысила $1 млрд?", "Компания-радуга", "Компания-нарвал", "Компания-единорог", "Компания-дедакорн", 3);
        addQuestion(q10);
        Question q11 = new Question("Как называется канал, соединяющий конечное оборудование с узлом доступа провайдера?", "Последняя миля", "Последняя точка", "Магистральная линия", "Распределительная линия", 1);
        addQuestion(q11);
        Question q12 = new Question("Назовите самый популярный язык программирования на данный момент?", "C#", "Python", "Java", "JavaScript", 4);
        addQuestion(q12);
        Question q13 = new Question("Какое наибольшее число можно закодировать с помощью одного байта?", "256", "255", "225", "226", 2);
        addQuestion(q13);
        Question q14 = new Question("На основе какого стандарта создана технология Wi-fi?", "IEEE 802.3", "IEEE 802.8", "IEEE 802.6", "IEEE 802.11", 4);
        addQuestion(q14);
        Question q15 = new Question("Какое максимальное число десятичное можно закодировать в 6 разрядах двоичного кода?", "31", "63", "81", "56", 2);
        addQuestion(q15);
        Question q16 = new Question("Как называется первая версия android?", "Eclair", "KitKat", "Apple Pie", "Donut", 3);
        addQuestion(q16);
        Question q17 = new Question("Какого режима работы микропроцессора не существует?", "Защищенный", "Реальный", "Системный", "Виртуальный", 4);
        addQuestion(q17);
        Question q18 = new Question("Какой из этих носителей информации появился позже?", "Floppy Disk", "Compact Disk", "Digital Versatile Disk", "Blu-ray Disk", 4);
        addQuestion(q18);
        Question q19 = new Question("В каком году выпустили Windows 7?", "2006", "2007", "2008", "2009", 4);
        addQuestion(q19);
        Question q20 = new Question("Папку с каким названием невозможно создать в ОС Windows?", "con", "microsoft", "bin", "etc", 1);
        addQuestion(q20);
        Question q21 = new Question("В каком году вышла самая первая ОС Windows?", "1981", "1989", "1985", "1983", 3);
        addQuestion(q21);
        Question q22 = new Question("Кто изобрел компьютерную мышь?", "Дуглас Энгельбарт", "Кристофер Скоулз", "Пол Бенер", "Дональд Дэвис", 1);
        addQuestion(q22);
        Question q23 = new Question("Первая дискета увидела свет в 1971 году, каков был ее объем?", "80 Кбайт", "256 Кбайт", "1 Мбайт", "720 Кбайт", 1);
        addQuestion(q23);
        Question q24 = new Question("Какой из этих процессоров создан компанией Intel?", "Geode", "Opteron", "Atom", "Duron", 3);
        addQuestion(q24);
        Question q25 = new Question("Гордон Мур является одним из основателей компании?", "AMD", "Intel", "Microsoft", "MediaTek", 2);
        addQuestion(q25);
        Question q26 = new Question("Какой из этих процессоров создан компанией AMD?", "Phenom", "Itanium", "Celeron", "Pentium", 1);
        addQuestion(q26);
        Question q27 = new Question("Какой ученый сконструировал первую вычислительную машину?", "Норберт Винер", "Виктор Глушков", "Чарльз Бэббидж", "Алан Тьюринг", 3);
        addQuestion(q27);
        Question q28 = new Question("В 1982 году две компании представили миру первый CD-Disk, назовите эти компании?", "Windows Sony", "Philips Toshiba", "Sony и Philips", "Apple Sony", 3);
        addQuestion(q28);
        Question q29 = new Question("В каком году выпустили первый массовый персональный компьютер IBM PC?", "1979", "1980", "1981", "1982", 3);
        addQuestion(q29);
        Question q30 = new Question("Какую дату считают Днем Рождения Интернета?", "19 августа 1991", "23 апреля 1992", "17 мая 1991", "31 января 1990", 3);
        addQuestion(q30);
    }

    private void addQuestion(Question question)
    {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, cv);
    }

    public ArrayList<Question> getAllQuestions()
    {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME, null);
        if (c.moveToFirst())
        {
            do
            {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
