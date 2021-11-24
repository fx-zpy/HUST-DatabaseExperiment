package database.S_T_XXXXXXXXXX;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class mysql_connect {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //在3306/后面是你要连接的数据库的名称，我这里名称中带有学号，隐去了
    static final String DB_URL = "jdbc:mysql://localhost:3306/S_T_XXXXXXXXXX?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    static final String USER = "root";//mysql用户（注册的时候的用户，一般都为root）
    static final String PASS = "*********";//mysql密码

    Scanner sc=new Scanner(System.in);
    private static Connection conn;
    /**
     * 初始化连接数据库
     */
    public static void MysqlConfig(){
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1号功能，增加学生信息
     * sql语句:insert into student values(Sno,Sname,Ssex,Sage,Sdept,Scholarship);
     * @param sc
     */
    public static void AddStudentInfo(Scanner sc){
        try{
            System.out.println("请输入学号(9位):");
            String Sno=sc.next();
            System.out.println("请输入姓名:");
            String Sname=sc.next();
            System.out.println("请输入性别(男/女):");
            String Ssex=sc.next();
            System.out.println("请输入年龄:");
            int Sage=sc.nextInt();
            System.out.println("请输入院系(英文简称，例如计算机学院为CS):");
            String Sdept=sc.next();
            System.out.println("请输入是否获取过奖学金(是/否):");
            String Scholarship=sc.next();

            String sql="insert into student values(?,?,?,?,?,?)";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,Sno);
            ps.setString(2,Sname);
            ps.setString(3,Ssex);
            ps.setInt(4,Sage);
            ps.setString(5,Sdept);
            ps.setString(6,Scholarship);
            int count=ps.executeUpdate();
            if(count>0){
                System.out.println(count + "条学生信息添加成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 2号功能，修改学生数据
     * sql语句:update student set Sname=Sname,Ssex=Ssex,Sage=Sage,Sdept=Sdept,Scholarship=Scholarship where Sno=Sno;
     * @param sc
     */
    public static void ReviseStudentInfo(Scanner sc) {
        String Sname=null;
        String Ssex=null;
        String Sdept=null;
        int Sage=0;
        String Scholarship=null;
        System.out.println("请输入学生的学号");
        String Sno=sc.next();
        try{

            String sql="select * from student where Sno="+Sno;
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            rs.next();
            Sname=rs.getString("Sname");
            Ssex=rs.getString("Ssex");
            Sage=rs.getInt("Sage");
            Sdept=rs.getString("Sdept");
            Scholarship=rs.getString("Scholarship");
            rs.close();
            ps.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try{
            System.out.println("请输入修改的数据序号(1:姓名2:性别3:年龄4:院系5:奖学金)");
            int op=sc.nextInt();
            switch(op){
                case 1:
                    System.out.println("请输入修改后的姓名:");
                    String Sname1=sc.next();
                    Sname=Sname1;
                    break;
                case 2:
                    System.out.println("请输入修改后的性别:");
                    String Ssex1=sc.next();
                    Ssex=Ssex1;
                    break;
                case 3:
                    System.out.println("请输入修改后的年龄:");
                    int Sage1=sc.nextInt();
                    Sage=Sage1;
                    break;
                case 4:
                    System.out.println("请输入修改后的院系:");
                    String Sdept1=sc.next();
                    Sdept=Sdept1;
                    break;
                case 5:
                    System.out.println("请输入修改后的奖学金情况:");
                    String Scholarship1=sc.next();
                    Scholarship=Scholarship1;
                    break;
                default:
                    break;
            }
            String sql1="update student set Sname=?,Ssex=?,Sage=?,Sdept=?,Scholarship=? where Sno="+Sno;
            PreparedStatement ps1=conn.prepareStatement(sql1);
            ps1.setString(1,Sname);
            ps1.setString(2,Ssex);
            ps1.setInt(3,Sage);
            ps1.setString(4,Sdept);
            ps1.setString(5,Scholarship);
            int count=ps1.executeUpdate();
            if(count>0){
                System.out.println("学号为" + Sno + "的学生数据已经修改");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 3号功能，增加课程信息
     * sql语句:insert into course values(Cno,Cname,Cpno,Ccredit);
     * @param sc
     */
    public static void AddCourseInfo(Scanner sc) {
        try{
            System.out.println("请输入课程序号:");
            String Cno=sc.next();
            System.out.println("请输入课程名称:");
            String Cname= sc.next();
            System.out.println("请输入现行课:");
            String Cpno= sc.next();
            System.out.println("请输入该课的学分:");
            int Ccredit=sc.nextInt();
            String sql="insert into course values(?,?,?,?)";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,Cno);
            ps.setString(2,Cname);
            ps.setString(3,Cpno);
            ps.setInt(4,Ccredit);
            int count=ps.executeUpdate();
            if(count>0){
                System.out.println(count + "条课程数据已经增加");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 4号功能，修改课程信息
     * sql语句:update course set Cname=Cname,Cpno=Cpno,Ccredit=Ccredit where Cno=Cno;
     * @param sc
     */
    public static void ReviseCourseInfo(Scanner sc) {
        String Cname=null;
        String Cpno=null;
        int Ccredit=0;
        System.out.println("请输入需要修改的课程的课程号:");
        String Cno=sc.next();
        try{
            String sql="select * from course where Cno="+Cno;
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            rs.next();
            Cname=rs.getString("Cname");
            Cpno=rs.getString("Cpno");
            Ccredit=rs.getInt("Ccredit");
            rs.close();
            ps.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("请输入你想要修改的内容序号(1:课程名称2:现行课3:学分):");
        int op=sc.nextInt();
        switch(op){
            case 1:
                System.out.println("请输入新的课程名称:");
                String Cname1=sc.next();
                Cname=Cname1;
                break;
            case 2:
                System.out.println("请输入新的现行课序号:");
                String Cpno1=sc.next();
                Cpno=Cpno1;
                break;
            case 3:
                System.out.println("请输入新的学分:");
                int Ccredit1=sc.nextInt();
                Ccredit=Ccredit1;
                break;
            default:
                break;
        }
        try{
            String sql="update course set Cname=?,Cpno=?,Ccredit=? where Cno="+Cno;
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,Cname);
            ps.setString(2,Cpno);
            ps.setInt(3,Ccredit);
            int count=ps.executeUpdate();
            if(count>0){
                System.out.println("课程号为" + Cno + "的课程信息已经修改");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    /**
     * 5号功能，删除无人选课的课程
     * sql语句:delete from course where Cno not in (select distinct Cno from sc);
     * @param sc
     */
    public static void DeleteNoPersonCourse(Scanner sc) {
        try{
            String sql="set FOREIGN_KEY_CHECKS=0";//禁用外键检查
            PreparedStatement ps=conn.prepareStatement(sql);
            int flag=ps.executeUpdate();

            String sql1="delete from course where Cno not in (select distinct Cno from sc)";
            PreparedStatement ps1=conn.prepareStatement(sql1);
            int count=ps1.executeUpdate();
            if(count>0){
                System.out.println("一共有" + count + "门课程没有学生选，已经被删除");
            }
            String sql2="set FOREIGN_KEY_CHECKS=1";//重新启用外键检查
            PreparedStatement ps2=conn.prepareStatement(sql2);
            int flag1=ps2.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    /**
     * 6号功能，录入学生成绩
     * sql语句:insert into sc values(Sno,Cno,Grade);
     * @param sc
     */
    public static void AddStudentSc(Scanner sc) {
        try{
            System.out.println("请输入学生的学号:");
            String Sno=sc.next();
            System.out.println("请输入学生的课程序号:");
            String Cno=sc.next();
            System.out.println("请输入学生成绩:");
            int Grade=sc.nextInt();
            String sql="insert into sc values(?,?,?)";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,Sno);
            ps.setString(2,Cno);
            ps.setInt(3,Grade);
            int count=ps.executeUpdate();
            if(count>0){
                System.out.println(count + "条学生成绩数据已经录入系统");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 7号功能，修改学生成绩
     * sql语句:update sc set Grade=Grade where Sno=Sno and Cno=Cno;
     * @param sc
     */
    public static void ReviseStudentSc(Scanner sc) {
        System.out.println("请输入学生学号:");
        String Sno=sc.next();
        System.out.println("请输入学生的课程序号:");
        String Cno=sc.next();
        try{
            System.out.println("请输入学生修改后的成绩:");
            int Grade=sc.nextInt();
            String sql="update sc set Grade=? where Sno=? and Cno=?";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,Grade);
            ps.setString(2,Sno);
            ps.setString(3,Cno);
            int count=ps.executeUpdate();
            if(count>0){
                System.out.println("学号为" + Sno + "的学生的" + Cno + "号课程成绩已经修改");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     * 8号功能，按照院系统计学生成绩
     * 成绩包含最高分、最低分、平均成绩、优秀率、不及格人数
     * @param sc
     */
    public static void CountGradeByDept(Scanner sc) {
        try{
            String sql = "select Sdept, avg(Grade) avgGrade, max(Grade) maxGrade, min(Grade) minGrade from (select Sdept, Grade from Student, SC where Student.Sno = SC.Sno) as deptGrade(Sdept, Grade) group by Sdept";
            String sql1=null;
            PreparedStatement ps=conn.prepareStatement(sql);
            PreparedStatement ps1=null;
            System.out.println("|----------|-----|-----|-------|----------|--------|");
            System.out.println(String.format("|%-9s|%-3s|%-3s|%-5s|%-8s|%-5s|", "院系", "最高分", "最低分","平均分", "优秀率", "不及格人数"));
            System.out.println("|----------|-----|-----|-------|----------|--------|");
            String Sdept= null;
            String avgGrade = null;
            String maxGrade = null;
            String minGrade = null;
            int total_num;
            int excellent_num;
            int fail_num;
            ResultSet rs=ps.executeQuery();
            ResultSet rs1=null;
            while (rs.next()) {
                Sdept=rs.getString("Sdept");
                avgGrade=rs.getString("avgGrade");
                maxGrade=rs.getString("maxGrade");
                minGrade=rs.getString("minGrade");

                sql1="select count(*) as count from sc, student where student.Sdept = ? and student.Sno = sc.Sno";
                ps1=conn.prepareStatement(sql1);
                ps1.setString(1,Sdept);
                rs1=ps1.executeQuery();
                rs1.next();
                total_num=rs1.getInt("count");
                rs1.close();

                sql1="select count(*) as count  from sc, student where student.Sdept = ? and student.Sno = sc.Sno and sc.Grade >= 80";
                ps1=conn.prepareStatement(sql1);
                ps1.setString(1,Sdept);
                rs1=ps1.executeQuery();
                rs1.next();
                excellent_num=rs1.getInt("count");
                rs1.close();

                sql1="select count(*) as count  from sc, student where student.Sdept = ? and student.Sno = sc.Sno and sc.Grade <= 60";
                ps1=conn.prepareStatement(sql1);
                ps1.setString(1,Sdept);
                rs1=ps1.executeQuery();
                rs1.next();
                fail_num=rs1.getInt("count");
                rs1.close();

                System.out.println(String.format("|%-10s|%-5s|%-5s|%-6s|%-10.2f|%-8s|", Sdept,maxGrade,minGrade,avgGrade,(double)excellent_num/total_num,fail_num));
                System.out.println("|----------|-----|-----|-------|----------|--------|");

            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 9号功能，按系进行排名，同时显示出学生、课程和成绩信息
     * @param sc
     */
    public static void RankByDept(Scanner sc) {
        ArrayList<student> stu=new ArrayList<student>();

        PreparedStatement ps=null;
        PreparedStatement ps1=null;
        ResultSet rs=null;
        ResultSet rs1=null;
        String sql=null;
        String Sdept=null;
        String name=null;
        double grade = 0;
        try{
            sql="select distinct Sdept from student";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
                stu.clear();
                Sdept=rs.getString("Sdept");
                sql="select Sname, avg(Grade) as avgGrade from (select Sname, student.Sno, Grade from student, sc where student.Sno = sc.Sno and student.Sdept = ?) as deptStudent(Sname, Sno, Grade) group by Sname";
                ps1=conn.prepareStatement(sql);
                ps1.setString(1,Sdept);
                rs1=ps1.executeQuery();


                while (rs1.next()){
                    student s=new student();
                    name=rs1.getString("Sname");
                    grade=rs1.getDouble("avgGrade");
                    s.setDept(Sdept);
                    s.setGrade(grade);
                    s.setName(name);
                    stu.add(s);
                }

                Collections.sort(stu,((o1, o2) -> (int) (o2.getGrade()-o1.getGrade())));
                System.out.println("--------------------------");
                System.out.println("        "+Sdept + "系排名");
                System.out.println("--------------------------");
                System.out.println("|排    名|姓    名|分     数|");
                System.out.println("--------------------------");
                for (int i = 0; i < stu.size(); i++) {
                    System.out.println(String.format("|%-7d|%-6s|%-8.2f|", i + 1, stu.get(i).getName(), stu.get(i).getGrade()));
                }
                System.out.println("*********************************");
                rs1.close();
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     * 10号功能，由学号得学生信息
     * @param sc
     */
    public static void GetInfoByNo(Scanner sc) {
        System.out.println("请输入需要查询的学生的学号:");
        String Sno=sc.next();
        String Sname=null;
        String Ssex=null;
        int Sage;
        String Sdept=null;
        String Scholarship=null;
        String Cno=null;
        String Cname=null;
        String Grade=null;
        try{
            String sql="select * from student where student.Sno="+Sno;
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            rs.next();
            Sname=rs.getString("Sname");
            Ssex=rs.getString("Ssex");
            Sage=rs.getInt("Sage");
            Sdept=rs.getString("Sdept");
            Scholarship=rs.getString("Scholarship");
            System.out.println("------------------------------");
            System.out.println("|          学生基本信息         |");
            System.out.println("------------------------------");
            System.out.println("学号:"+Sno);
            System.out.println("姓名:" + Sname);
            System.out.println("性别:" + Ssex);
            System.out.println("年龄:" + Sage);
            System.out.println("院系:" + Sdept);
            System.out.println("奖学金获得情况:" + Scholarship);
            System.out.println("------------------------------");
            System.out.println("***********************************************");
            rs.close();ps.close();

            String sql1="select sc.Cno,Grade,course.Cname from sc,course where sc.Cno=course.Cno and sc.Sno="+Sno;
            PreparedStatement ps1=conn.prepareStatement(sql1);
            ResultSet rs1=ps1.executeQuery();
            System.out.println("------------------------------");
            System.out.println("|          学生课程成绩         |");
            System.out.println("------------------------------");
            System.out.println("|课 程 序 号|课 程 名 称|成    绩|");
            System.out.println("------------------------------");
            while (rs1.next()) {
                Cno=rs1.getString("Cno");
                Cname=rs1.getString("Cname");
                Grade=rs1.getString("Grade");
                System.out.println(String.format("|%-9s|%-8s|%-8s|", Cno, Cname, Grade));
                System.out.println("------------------------------");

            }
            System.out.println("***********************************************");
            rs1.close();
            ps1.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    /**
     * 11号附属功能，查看学生表信息
     * sql语句:select * from student;
     * @param sc
     */
    public static void CheckStudentInfoAll(Scanner sc) {
        try{
            String sql="select * from student";
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            System.out.println("|----------|------|----|----|------|----|");
            System.out.println(String.format("|%-9s|%-6s|%-4s|%-4s|%-4s|-%4s|","学号","姓名","性别","年龄","院系","奖学金"));
            System.out.println("|----------|------|----|----|------|----|");
            String Sno=null;
            String Sname=null;
            String Ssex=null;
            int Sage=0;
            String Sdept=null;
            String Scholarship=null;
            while(rs.next()){
                Sno=rs.getString("Sno");
                Sname=rs.getString("Sname");
                Ssex=rs.getString("Ssex");
                Sage=rs.getInt("Sage");
                Sdept=rs.getString("Sdept");
                Scholarship=rs.getString("Scholarship");
                System.out.println(String.format("|%-10s|%-6s|%-4s|%-4d|%-6s|%-4s|", Sno, Sname, Ssex, Sage, Sdept, Scholarship));
                System.out.println("|----------|------|----|----|------|----|");
            }
            rs.close();
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 12号附属功能，查看课程表信息
     * sql语句:select * from course;
     * @param sc
     */
    public static void CheckCourseInfoAll(Scanner sc) {
        try{
            String sql="select * from course";
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            System.out.println("|-------|----------|-----|----|");
            System.out.println(String.format("|%-5s|%-7s|%-3s|%-3s|", "课程序号", "课程名称", "现行课", "学分"));
            System.out.println("|-------|----------|-----|----|");
            String Cno=null;
            String Cname=null;
            String Cpno=null;
            int Ccredit=0;
            while (rs.next()) {
                Cno=rs.getString("Cno");
                Cname=rs.getString("Cname");
                Cpno=rs.getString("Cpno");
                Ccredit=rs.getInt("Ccredit");
                System.out.println(String.format("|%-7s|%-8s|%-5s|%-3d|", Cno, Cname, Cpno, Ccredit));
                System.out.println("|-------|----------|-----|----|");
            }
            rs.close();
            ps.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 13号附属功能，查看成绩表信息
     * sql语句:select * from sc;
     * @param sc
     */
    public static void CheckScInfoAll(Scanner sc) {
        try{
            String sql="select * from sc";
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            System.out.println("|-------------|----------|--------|");
            System.out.println(String.format("|%-12s|%-7s|%-7s|", "学号", "课程序号", "成绩"));
            System.out.println("|-------------|----------|--------|");
            String Sno=null;
            String Cno=null;
            int Grade=0;
            while (rs.next()) {
                Sno=rs.getString("Sno");
                Cno=rs.getString("Cno");
                Grade=rs.getInt("Grade");
                System.out.println(String.format("|%-13s|%-10s|%-8d|", Sno, Cno, Grade));
                System.out.println("|-------------|----------|--------|");
            }
            rs.close();
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
