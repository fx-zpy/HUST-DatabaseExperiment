package database.S_T_XXXXXXXXXX;

import java.util.Scanner;

public class dbmain {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int op;
        mysql_connect.MysqlConfig();//初始化连接数据库
        while(true){
            menu();
            op=sc.nextInt();
            switch(op){
                case 0:
                    return;
                case 1:
                    mysql_connect.AddStudentInfo(sc);
                    break;
                case 2:
                    mysql_connect.ReviseStudentInfo(sc);
                    break;
                case 3:
                    mysql_connect.AddCourseInfo(sc);
                    break;
                case 4:
                    mysql_connect.ReviseCourseInfo(sc);
                    break;
                case 5:
                    mysql_connect.DeleteNoPersonCourse(sc);
                    break;
                case 6:
                    mysql_connect.AddStudentSc(sc);
                    break;
                case 7:
                    mysql_connect.ReviseStudentSc(sc);
                    break;
                case 8:
                    mysql_connect.CountGradeByDept(sc);
                    break;
                case 9:
                    mysql_connect.RankByDept(sc);
                    break;
                case 10:
                    mysql_connect.GetInfoByNo(sc);
                    break;
                case 11:
                    mysql_connect.CheckStudentInfoAll(sc);
                    break;
                case 12:
                    mysql_connect.CheckCourseInfoAll(sc);
                    break;
                case 13:
                    mysql_connect.CheckScInfoAll(sc);
                    break;
                default:
                    break;
            }
        }

    }

    /**
     * 函数名：menu
     * 函数功能：提供用户选择菜单
     */
    private static void menu() {
        System.out.println("===================================================");
        System.out.println("==============请输入以下数字进行功能选择================");
        System.out.println("0:退出本次服务      1:增加学生信息       2:修改学生信息     ");
        System.out.println("3:增加课程信息      4:修改课程信息       5:删除无人课程     ");
        System.out.println("6:录入学生成绩      7:修改学生成绩       8:按系统计成绩     ");
        System.out.println("9:按系进行排名      10:由学号得信息     ");
        System.out.println("---------------------------------------------------");
        System.out.println("                     附属功能                      ");
        System.out.println("11:查看学生信息     12:查看课程信息      13:查看成绩信息     ");
        System.out.println("===================================================");
        System.out.println("请输入你的选择:");
    }
}
