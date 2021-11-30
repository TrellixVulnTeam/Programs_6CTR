var sqlite3 = require('sqlite3').verbose();
let db = new sqlite3.Database('sqlite\base.db', sqlite3.OPEN_READWRITE, (err) => {
    if (err) {
        return console.error(err.message);

    }
    console.log('Connection Success');
});//Przypisanie bazy do obiektu

db.serialize(() => {

    db.each('SELECT * FROM Rejestracja', (err, row) => {
        if (err) {
            console.error(err.message);
        }
        console.log(row.id + '\t' + row.login);
    });
});
// Wykonanie zapytania do bazy 



db.close((err) => {
    if (err) {
        return console.error(err.message);
    }
    console.log('Connection End');

});
//Koniec połączenia z bazą danych 
