# Week 6 Native
demovideon linkki: https://unioulu-my.sharepoint.com/:v:/g/personal/jlievone24_students_oamk_fi/IQA9aCPTOwdZQLhXzfA5hbEhAVvjruZ3VYRjlyMSoPJ2l2s?e=0zGh6Z&nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJTdHJlYW1XZWJBcHAiLCJyZWZlcnJhbFZpZXciOiJTaGFyZURpYWxvZy1MaW5rIiwicmVmZXJyYWxBcHBQbGF0Zm9ybSI6IldlYiIsInJlZmVycmFsTW9kZSI6InZpZXcifX0%3D

## Lyhyesti
Room on Androidin ORM (Object Relational Mapping) tietokanta joka tarjoaa turvallisen ja helpon tavan tallentaa sovelluksen dataa SQLiteen. Sovelluksessa on käytössä seuraavat kerrokset:

Entity 
TaskEntity (data class Task) määrittelee tietokantataulun rakenteen.

DAO 
TaskDao tarjoaa CRUD-operaatiot (insert, update, delete, query).

Database
AppDatabase yhdistää DAO:t ja luo tietokannan instanssin.

Repository 
TaskRepository kapseloi DAO:n ja tarjoaa Flow- tai suspend-funktiot UI:lle.

ViewModel 
TaskViewModel kerää Flow-datan ja hallitsee UI:n tilaa.

UI 
Jetpack Compose -näytöt kuuntelevat ViewModelin tilaa ja päivittyvät automaattisesti muutoksista.

## Projektin rakenne
/data/model/Task.kt
/data/model/TaskEntity.kt

/data/local/TaskDao.kt
/data/local/AppDatabase.kt

/data/repository/TaskRepository.kt

/navigation/Routes.kt

/view/AddDialog.kt
/view/CalendarScreen.kt
/view/DetailDialog.kt
/view/HomeScreen.kt
/view/SettingsScreen.kt

/viewmodel/TaskViewModel.kt
/viewmodel/TaskViewModelFactory.kt

/MainActivity.kt

model sisältää dataluokat, local sisältää Room-tietokannan ja DAO:t, repository sisältää Repository-luokan, viewmodel hallitsee sovelluksen tilan, view sisältää Composable-näkymät

## Datan virta

UI reagoi käyttäjän toimiin (lisää, poista, muokkaa taskia), jolloin ViewModel kutsuu Repositoryn metodeja. Repository sitten käyttää DAO:ta ja päivittää Room-tietokannan. Muutokset palautuvat Flow:n kautta ViewModeliin ja Composable-näkymät kuuntelevat Flow:ta collectAsState() ja päivittyvät automaattisesti.
