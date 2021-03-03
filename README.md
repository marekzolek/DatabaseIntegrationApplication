# DatabaseIntegrationApplication
> Aplikacja utworzona na potrzeby skolenia wewnętrznego

## Spis treści
* [Informacje](#general-info)
* [Technologie](#technologies)
* [Status](#status)

## Informacje

Zadanie polegało na możliwości zintegrowania kilku baz danych w jednym narzędziu, jeśli jest taka możliwość. Zakładając, że jest taka sama struktura bazy danych, 
lecz przykładowo nazwy kolumn się różnią, za pomocą tego narzędzia możliwe jest zintegrowanie kilku baz danych. Aby to zrobić nalezy podać niezbędne dane dostępu
do konkretnych baz danych. w następnym kroku łączymy odpowiednio kolumny, po czym uzyskujemy dane z kilku baz. 

Przykładowo jeden właściciel posiada kilka aptek, każda apteka posiada osobną bazę danych. To narzędzie umożliwi właścicielowi dostęp do kilku baz z aptek w jednym narzędziu.

Założeniem była możliwość obsługiwania 3 baz, z czego jedna miała być jako plik excel. Bazy danych jakie obsługuje to H2 oraz MySQL. W projekcie wykorzystałem 
spring-jdbc w celu komunikacji z bazami.

## Technologie
* Java Swing
* Spring JDBC
* H2 database
* MySQL database
* Apache POI

## Status
Projekt jest zakończony, nie planuję kontynuowania tego projektu. W planach mam zrealizowanie podobnego projektu za pomocą Spring Boota + Angulara w celu nauki frontendu.
