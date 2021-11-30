const express = require('express');// Odwołanie się do routera dostępnego w expresie
const session = require('express-session');
const router = express.Router();
const sqlite3 = require('sqlite3').verbose(); // importowanie modułu sqlite3

router.use(session({
    secret: 'to jest sekret'
}));

let ifAuth = function(req, res, ok, err){
    console.log(typeof redirect);
    if(req.session.logged){
        if(typeof ok === 'string'){
            res.redirect(ok);
        }else{
            ok(req, res);
        }
    }else{
        if(typeof err === 'string'){
            res.redirect(err);
        }else{
            err(req, res);
        }
    }
}

let USERLEVEL = {
    ADMIN: 'A',
    USER: 'U'
}

let getUser = function(req){
    let user = {
        isLogged: req.session.logged,
        login: req.session.loggedUser,
        level: req.session.level,
        isAdmin: function(){
            return user.level === USERLEVEL.ADMIN;
        }
    };

    return user;
};

let setUserData = function(req, login, level){
    if(login == null){
        req.session.logged = false;
        req.session.loggedUser = null;
        req.session.level = null;
    }else{
        req.session.logged = true;
        req.session.loggedUser = login;
        req.session.level = level;
    }
};



router.get('/',(req, res) => {
    res.render('home', {session: req.session, user: getUser(req)});
   
});

router.get('/contact',(req, res) => {
    res.render('contact');
});

router.get('/logowanie',(req, res) => {
    res.render('logowanie', {session: req.session});
});

router.get('/catalog', function(req, res){
    ifAuth(req, res, function(req, res){
        let db = new sqlite3.Database("base.db", sqlite3.OPEN_READWRITE, function(err){
            if(err){return;}
            let query = "SELECT * FROM ksiazki";
            db.all(query, (err, row) => {
                res.render('catalog', {
                    user: getUser(req),
                    books: row
                });
            });
            db.close(); // Zamykanie bazy 
            
        });
        
    }, '/logowanie');
});



router.post('/login',(req, res) => {
    let login = req.body.login;
    let password = req.body.password;

    let db = new sqlite3.Database("base.db", sqlite3.OPEN_READWRITE, (err) => { 
        if (err) { 
            console.log('BAZA SIĘ NIE ŁĄCZY', err) 
        } else{
            let query= `SELECT login, level FROM rejestracja WHERE login = '${login}' AND password = '${password}'`;
            db.all(query, (err, row) => {
                 if (err) {
                      console.log(err);
                 }
                 if(row.length == 1){
                    setUserData(req, login, row[0].level);
                    res.redirect('/');
                    //res.render('login',{login, password, data: booksData});
                 }else{
                    let error = "Podane przez Ciebie konto nie istnieje. Spróbuj ponownie." 
                    res.render('logowanie',{error});
                }
            });  // Obsługa logowania i uzupełnienie tabel
            
        }
        db.close(); // Zamykanie bazy 
    })  
});


router.get('/rejestracja',(req, res) => {
    res.render('rejestracja');
});

router.post('/register',(req, res) => {

    let log = req.body.log;
    let pass = req.body.pass;

    let db = new sqlite3.Database("base.db", sqlite3.OPEN_READWRITE, (err) => { 
        if (err) { 
            console.log('BAZA SIĘ NIE ŁĄCZY', err) 
        } else
        {
            let queryadd = `INSERT INTO rejestracja (login, password) VALUES ("${log}","${pass}")`;

            db.run(queryadd, (err, row) => {
                if (err) {
                     console.log(err);

                /*}else if(row.login==log)
                {
                    let error1 = 'Konto o podanym haśle lub loginie istnieje';
                        res.render('rejestracja', {error1});
                */
               // Napisać warunek że jeśli taki użytkownik istnieje to wysłać komunikat 
               
                }else
                {
                    let check=`SELECT login, password FROM rejestracja WHERE login LIKE '${log}' OR password LIKE '${pass}'`
                    console.log(row);
                
                    res.render('register',{log, pass});
                }  
       });  
       
        }
    });

 // Obsługa rejestracji do strony   
});

router.get('/logout', function(req,res)
{ 
    setUserData(req, null);
    res.redirect('/');
}

)


module.exports = router; // dodaje router do obiektu