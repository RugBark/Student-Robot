пакет org.usfirst.frc.team5567.robot;

импорт edu.wpi.first.wpilibj.GenericHID.Hand;
импорт edu.wpi.first.wpilibj.IterativeRobot;
импорт edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
импорт edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
импорт edu.wpi.first.wpilibj.SpeedController;
импорт edu.wpi.first.wpilibj.XboxController;
импорт edu.wpi.first.wpilibj.RobotDrive;
импорт edu.wpi.first.wpilibj.Victor;
импорт edu.wpi.first.wpilibj.RobotDrive.MotorType;
импорт edu.wpi.first.wpilibj.Timer;
импорт edu.wpi.first.wpilibj.Talon;
/ **
 * ВМ сконфигурирована для автоматического запуска данного класса, а также для вызова
 * Функции, соответствующие каждому режиму, как это описано в IterativeRobot
 * документация. Если изменить имя этого класса или пакета после
 * Создание этого проекта, необходимо также обновить файл манифеста в ресурсе
 * Каталог.
 * /
Класс Robot публика расширяет IterativeRobot {
Окончательный Строка defaultAuto = "По умолчанию";
Окончательный Строка customAuto = "Мой Авто";
Строка автоотобран;
SendableChooser <String> выбирающий = новый SendableChooser <> ();
INT FrontLeft = 0;
INT FrontRight = 1;
INT RearLeft = 2;
INT RearRight = 3;
SpeedController FLController = новый Talon (FrontLeft);
SpeedController FRController = новый Victor (FrontRight);
SpeedController RLController = новый Talon (RearLeft);
SpeedController RRController = новый Talon (RearRight);
RobotDrive Team = новый RobotDrive (FLController, FRController, RLController, RRController);
Таймер Альфа = новый таймер ();
XboxController CBETA = новый XboxController (0);
XboxController CBravo = новый XboxController (1);
двойная дельта = 0,1;
Лебедка robotWinch = новый Лебедка (4);
Shooter robotShoooter = новый шутер (5);




/ **
* Эта функция выполняется, когда робот сначала запускается и должно быть
* Используется для любого кода инициализации.
* /
@Override
общественного недействительными robotInit () {
chooser.addDefault ( "По умолчанию Auto", defaultAuto);
chooser.addObject ( "Мой Авто", customAuto);
SmartDashboard.putData ( «Авто выбор», выбирающий);
Team.setInvertedMotor (MotorType.kFrontLeft, правда);
Team.setInvertedMotor (MotorType.kRearLeft, правда);

}

/ **
* Это автономное (вместе с кодом CHOOSER выше) показывает, как выбрать
* Между различными автономными режимами с помощью панели. sendable
* Выбирающий код работает с Java SmartDashboard. Если вы предпочитаете
* LabVIEW Dashboard, удалить весь код CHOOSER и раскомментируйте
* GetString линии, чтобы получить имя авто из текстового поля ниже гироскопом
*
* Вы можете добавить дополнительные режимы авто путем добавления дополнительных сравнения с
* Структура переключатель ниже с дополнительными строками. Если используется
* SendableChooser убедитесь, чтобы добавить их к коду CHOOSER выше, а также.
* /
@Override
общественного недействительными autonomousInit () {
автоотобран = chooser.getSelected ();
// Автоотобран = SmartDashboard.getString ( "Auto Selector",
// DefaultAuto);
System.out.println ( "Auto выбрано:" + автоотобран);
this.Alpha.reset ();
this.Alpha.start ();
}

/ **
* Эта функция вызывается периодически во время автономной
* /
@Override
общественного недействительными autonomousPeriodic () {
Переключатель (автоотобран) {
случай customAuto:
// Помещаем пользовательский автоматический код здесь
ломать;
случай defaultAuto:
по умолчанию:
// Помещаем код авто по умолчанию здесь
this.Alpha.get ();
если (Alpha.get () <= 5.00) {
Team.mecanumDrive_Cartesian (0, 0,5, 0, 0);
}
еще {
Team.mecanumDrive_Cartesian (0, 0, 0, 0);
}

ломать;
}
}

/ **
* Эта функция вызывается периодически во время управления оператора
* /
@Override
общественного недействительными teleopPeriodic () {
двойная XLIn = this.CBeta.getX (Hand.kLeft);
двойная YLIn = this.CBeta.getY (Hand.kLeft);
двойная LtIn = this.CBeta.getTriggerAxis (Hand.kLeft);
двойная RtIn = this.CBeta.getTriggerAxis (Hand.kRight);
если (XLIn <Дельта && XLIn> Гамма-Дельта) {
XLIn = 0;
}
если (YLIn <Дельта && YLIn> Гамма-Дельта) {
YLIn = 0;
}
Team.mecanumDrive_Cartesian (XLIn, YLIn, RtIn-LtIn, 0);
двойная BLtIn = this.CBravo.getTriggerAxis (Hand.kLeft);
двойная BRtIn = this.CBravo.getTriggerAxis (Hand.kRight);
this.robotWinch.setWinchSpeed ​​(BLtIn);
}

/ **
* Эта функция вызывается периодически во время тестового режима
* /
@Override
общественного недействительными testPeriodic () {
}
}
paket org.usfirst.frc.team5567.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Talon;
/ **
 * VM skonfigurirovana dlya avtomaticheskogo zapuska dannogo klassa, a takzhe dlya vyzova
 * Funktsii, sootvetstvuyushchiye kazhdomu rezhimu, kak eto opisano v IterativeRobot
 * dokumentatsiya. Yesli izmenit' imya etogo klassa ili paketa posle
 * Sozdaniye etogo proyekta, neobkhodimo takzhe obnovit' fayl manifesta v resurse
 * Katalog.
 * /
Klass Robot publika rasshiryayet IterativeRobot {
Okonchatel'nyy Stroka defaultAuto = "Po umolchaniyu";
Okonchatel'nyy Stroka customAuto = "Moy Avto";
Stroka avtootobran;
SendableChooser <String> vybirayushchiy = novyy SendableChooser <> ();
INT FrontLeft = 0;
INT FrontRight = 1;
INT RearLeft = 2;
INT RearRight = 3;
SpeedController FLController = novyy Talon (FrontLeft);
SpeedController FRController = novyy Victor (FrontRight);
SpeedController RLController = novyy Talon (RearLeft);
SpeedController RRController = novyy Talon (RearRight);
RobotDrive Team = novyy RobotDrive (FLController, FRController, RLController, RRController);
Taymer Al'fa = novyy taymer ();
XboxController CBETA = novyy XboxController (0);
XboxController CBravo = novyy XboxController (1);
dvoynaya del'ta = 0,1;
Lebedka robotWinch = novyy Lebedka (4);
Shooter robotShoooter = novyy shuter (5);




/ **
* Eta funktsiya vypolnyayetsya, kogda robot snachala zapuskayetsya i dolzhno byt'
* Ispol'zuyetsya dlya lyubogo koda initsializatsii.
* /
@Override
obshchestvennogo nedeystvitel'nymi robotInit () {
chooser.addDefault ( "Po umolchaniyu Auto", defaultAuto);
chooser.addObject ( "Moy Avto", customAuto);
SmartDashboard.putData ( «Avto vybor», vybirayushchiy);
Team.setInvertedMotor (MotorType.kFrontLeft, pravda);
Team.setInvertedMotor (MotorType.kRearLeft, pravda);

}

/ **
* Eto avtonomnoye (vmeste s kodom CHOOSER vyshe) pokazyvayet, kak vybrat'
* Mezhdu razlichnymi avtonomnymi rezhimami s pomoshch'yu paneli. sendable
* Vybirayushchiy kod rabotayet s Java SmartDashboard. Yesli vy predpochitayete
* LabVIEW Dashboard, udalit' ves' kod CHOOSER i raskommentiruyte
* GetString linii, chtoby poluchit' imya avto iz tekstovogo polya nizhe giroskopom
*
* Vy mozhete dobavit' dopolnitel'nyye rezhimy avto putem dobavleniya dopolnitel'nykh sravneniya s
* Struktura pereklyuchatel' nizhe s dopolnitel'nymi strokami. Yesli ispol'zuyetsya
* SendableChooser ubedites', chtoby dobavit' ikh k kodu CHOOSER vyshe, a takzhe.
* /
@Override
obshchestvennogo nedeystvitel'nymi autonomousInit () {
avtootobran = chooser.getSelected ();
// Avtootobran = SmartDashboard.getString ( "Auto Selector",
// DefaultAuto);
System.out.println ( "Auto vybrano:" + avtootobran);
this.Alpha.reset ();
this.Alpha.start ();
}

/ **
* Eta funktsiya vyzyvayetsya periodicheski vo vremya avtonomnoy
* /
@Override
obshchestvennogo nedeystvitel'nymi autonomousPeriodic () {
Pereklyuchatel' (avtootobran) {
sluchay customAuto:
// Pomeshchayem pol'zovatel'skiy avtomaticheskiy kod zdes'
lomat';
sluchay defaultAuto:
po umolchaniyu:
// Pomeshchayem kod avto po umolchaniyu zdes'
this.Alpha.get ();
yesli (Alpha.get () <= 5.00) {
Team.mecanumDrive_Cartesian (0, 0,5, 0, 0);
}
yeshche {
Team.mecanumDrive_Cartesian (0, 0, 0, 0);
}

lomat';
}
}

/ **
* Eta funktsiya vyzyvayetsya periodicheski vo vremya upravleniya operatora
* /
@Override
obshchestvennogo nedeystvitel'nymi teleopPeriodic () {
dvoynaya XLIn = this.CBeta.getX (Hand.kLeft);
dvoynaya YLIn = this.CBeta.getY (Hand.kLeft);
dvoynaya LtIn = this.CBeta.getTriggerAxis (Hand.kLeft);
dvoynaya RtIn = this.CBeta.getTriggerAxis (Hand.kRight);
yesli (XLIn <Del'ta && XLIn> Gamma-Del'ta) {
XLIn = 0;
}
yesli (YLIn <Del'ta && YLIn> Gamma-Del'ta) {
YLIn = 0;
}
Team.mecanumDrive_Cartesian (XLIn, YLIn, RtIn-LtIn, 0);
dvoynaya BLtIn = this.CBravo.getTriggerAxis (Hand.kLeft);
dvoynaya BRtIn = this.CBravo.getTriggerAxis (Hand.kRight);
this.robotWinch.setWinchSpeed ​​(BLtIn);
}

/ **
* Eta funktsiya vyzyvayetsya periodicheski vo vremya testovogo rezhima
* /
@Override
obshchestvennogo nedeystvitel'nymi testPeriodic () {
}
}
