package ru.pa4ok.demoexam;

public class Student extends Human
{
    private int level;

    public Student(String name, int age, boolean isWoman, int level)
    {
        super(name, age, isWoman); //вызов родительского конструктора
        this.level = level;
    }

    @Override
    public void test() {
        super.test();
        System.out.println("test from Student");
    }

    @Override
    public String toString() {
        return "Student{" +
                "level=" + level +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isWoman=" + isWoman +
                '}';
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
