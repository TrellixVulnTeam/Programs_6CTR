const app = require('./app'); //Podłączenie pliku app


app.set('port', process.env.PORT || 8080); // ustawienie portów z wykorzystaniem zmiennych środowiskowych 

const server = app.listen(app.get('port'), () => {  // Nasłuchiwanie portu 8080
    console.log(`Listening on ${server.address().port}`);
});