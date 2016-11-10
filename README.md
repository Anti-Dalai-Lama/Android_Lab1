#ANDROID LABA 1
TASK:
Разработать следующие экраны и объединить их общим экраном вызова (в виде нажимных картинок):
	1)	Color display – редактор цвета по трем основным составляющим RGB (задаются через SeekBar)
		Внешний вид экрана зависит от ориентации: в portrait ползунки снизу на всю ширину 
		равномерно, в landscape – справа. Занимают 1/3 высоты (в landscape ширины). Остальное 
		пространство занимает цветовая панель.
	2)	Калькулятор на 4 базовые арифметические операции с кнопками для ввода аргументов и 
		действий. Калькулятор должен резиново растягиваться в landscape ориентации и на 
		экранах разного разрешения.
	3)	Список заметок. У каждой заметки есть название, описание, важность (три уровня), 
		время и дата назначения и картинка (выбирается из галереи). В списке заметки 
		отображаются с иконкой из галереи и временем создания справа сверху более мелким 
		шрифтом, а также графическим отображением важности (иконка). Записки можно добавлять, 
		удалять, редактировать (без сохранения между перезапусками). Удаление/редактирование 
		вызываются из контекстного меню при долгом нажатии, добавление - из основного меню в 
		ActionBar. В ActionBar реализована поиск/фильтрация заметок по содержанию текста, а 
		также фильтрация по важности. Текстовые надписи локализованы для русского и английского языков.

DOCS:
	-Intent
	https://www.youtube.com/watch?v=06bcsMx-7QQ&index=35&list=PL6gx4Cwl9DGBsvRxJJOzG4r4k_zLKrnxl
	https://www.youtube.com/watch?v=mPGCLKRCG-8&index=36&list=PL6gx4Cwl9DGBsvRxJJOzG4r4k_zLKrnxl
	-ListView
	https://www.youtube.com/watch?v=U_Jvk4G28YE&index=45&list=PL6gx4Cwl9DGBsvRxJJOzG4r4k_zLKrnxl
	https://www.youtube.com/watch?v=A-_hKWMA7mk&index=46&list=PL6gx4Cwl9DGBsvRxJJOzG4r4k_zLKrnxl
	https://www.youtube.com/watch?v=_sStCBdJkQg&index=47&list=PL6gx4Cwl9DGBsvRxJJOzG4r4k_zLKrnxl
	https://www.youtube.com/watch?v=nOdSARCVYic&index=48&list=PL6gx4Cwl9DGBsvRxJJOzG4r4k_zLKrnxl
	https://www.youtube.com/watch?v=uic3TVp_j3M&index=76&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa
	https://www.youtube.com/watch?v=BSZLqBWKTHw&index=77&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa
	https://www.youtube.com/watch?v=JEZpcpKOaZo&index=78&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa
	https://www.youtube.com/watch?v=qK4gQ2aOpw0&index=83&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa
	https://www.youtube.com/watch?v=0zQCv0Xb3pk&index=84&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa
	https://www.youtube.com/watch?v=DyAArclyjwE&index=85&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa
	https://www.youtube.com/watch?v=GQXfiwY3glw&index=86&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa
	https://www.youtube.com/watch?v=PCBgcBgZTxk&index=87&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa
	https://www.youtube.com/watch?v=ExbAvpHMX2U&index=88&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa
	https://www.youtube.com/watch?v=h1yTifTO-ss&index=89&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa
	https://www.youtube.com/watch?v=KHTi28zT7rI&index=90&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa
	https://www.youtube.com/watch?v=mKGoKnhN_Ys&index=91&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa
	https://www.youtube.com/watch?v=OTEiRiMaQ7M&index=92&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa
	https://www.youtube.com/watch?v=ka5Tk7J9rG0&index=93&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa
	https://www.youtube.com/watch?v=EJBPStlIUvw&index=94&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa
	https://www.youtube.com/watch?v=qL2IyQxVi8k&index=95&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa
	https://www.youtube.com/watch?v=_l9e2t4fcfM&index=96&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa
	-StartActivityForResult
	https://www.youtube.com/watch?v=P2tNi1tS0xU&list=PL2F07DBCDCC01493A&index=49
	https://www.youtube.com/watch?v=AEA1qJFpheY&list=PL2F07DBCDCC01493A&index=50
	https://www.youtube.com/watch?v=nvC89kFKduQ&list=PLyfVjOYzujuitvmO3ttzfXFRldfS863RX&index=9
	https://www.youtube.com/watch?v=tj8ls_mDkZU&list=PLyfVjOYzujuitvmO3ttzfXFRldfS863RX&index=10
	https://www.youtube.com/watch?v=QZv6R7Zx_dE&list=PLIU76b8Cjem7oSU18zTKNfsyzrmf3Zubl&index=10
	-ActionBar
	https://www.youtube.com/watch?v=NYVcfa6Bke4&index=7&list=PLyfVjOYzujuiXQ0_8KfsGIG6zn95Jo7ZK
	https://www.youtube.com/watch?v=_39y02PMpBI&index=8&list=PLyfVjOYzujuiXQ0_8KfsGIG6zn95Jo7ZK
	https://www.youtube.com/watch?v=Dr2V1rY3vRM&index=1&list=PLyfVjOYzujuig79jEk6_VY_UqL8Aq0uGI
	-ContextMenu
	https://www.youtube.com/watch?v=EF4eEixzPo4&index=5&list=PLyfVjOYzujuiSEbZ_Aq6u-UA_symp9l6Z
	-Intent Виды вызовов
	https://www.youtube.com/watch?v=oVZ1cQS0w4w&list=PLIU76b8Cjem7oSU18zTKNfsyzrmf3Zubl&index=17
	-ListView and Adapter
	https://www.youtube.com/watch?v=eZFNkXAF1WM&list=PLIU76b8Cjem7oSU18zTKNfsyzrmf3Zubl&index=18
	-ActionBar Menu
	https://www.youtube.com/watch?v=IASDIoVfNOo&list=PLIU76b8Cjem7oSU18zTKNfsyzrmf3Zubl&index=19
	-ActionBar
	https://www.youtube.com/watch?v=SF3Hh31clqQ&index=37&list=PLS1QulWo1RIbb1cYyzZpLFCKvdYV_yJ-E
	-ActionBar back button
	https://www.youtube.com/watch?v=m7BWTh6xPsw&index=38&list=PLS1QulWo1RIbb1cYyzZpLFCKvdYV_yJ-E
	-SEARCH
	http://stackoverflow.com/questions/35447491/how-to-add-a-search-to-an-action-bar
	https://developer.android.com/guide/topics/search/search-dialog.html
	http://javapapers.com/android/android-searchview-action-bar-tutorial/
	https://coderwall.com/p/zpwrsg/add-search-function-to-list-view-in-android
	-LOCALIZATION
	https://developer.android.com/guide/topics/resources/localization.html
	https://developer.android.com/training/basics/supporting-devices/languages.html

