

const express = require('express'); // Podłączenie modułu expres by móc się odwołać 
const app = express(); 				// stała app do sterowania aplikacją (instancja)
const path = require('path'); 	// modół do podawania ścieżek	
const bodyParser = require('body-parser'); 
const cookieParser = require('cookie-parser'); // Dodanie modółów zainstalowanych wcześniej  
const flash = require('connect-flash');
const routes = require('./routes/index');// Import pliku index.js by go użyć
const sqlite3 = require('sqlite3'); //Modół bazy danych sqlite
app.set('views', path.join(__dirname, 'views')); // Podanie ścieżki do katalogu views gdzie będą widoki 
app.set('view engine', 'pug'); // użycie silnika szablonów pug
app.set(express.static(path.join(__dirname + '/public'))); // wskazanie katalogu wyświetlanych podstron
app.use(cookieParser());// Ustawienie obsługi ciasteczek 
app.use(express.static('public'));
app.use(bodyParser.json()); // Odwołanie do metody use w instancji aplikacji
app.use(bodyParser.urlencoded({extended: true}));// ustawienia ułatwiające odczytywanie danych np. z formularzy (POST GET) 
app.use(flash()); //konfiguracja obsługi tymczasowych komunikatów w aplikacji 

//Tworze katalog index.js w katalogu routes by zachować porządek przy konfiguracji ścieżek


app.use('/', routes); // By router obsługiwał wszystkie zapytania zaczynające się od slash


module.exports = app; // dodanie do obiektu module.exports
