// Janusz 
// Kowalski
// qwerty
require("node-localstorage");
var User = require('../models/user');
const bcrypt = require("bcryptjs");
const jwt = require("jsonwebtoken");
const { set } = require("mongoose");

var stan;

exports.logout = function(req,res)
{
    stan=req.stan;
    if(localStorage.getItem('token'))
    {
        localStorage.removeItem('token');
       
        res.redirect("/");


      
    
    }else // Jeśli ktoś na sztywno będzie chciał się wylogować
    {
        
        console.log("nikt nie jest zalogowany");
        res.render("logins", {stan: stan}, function(err, html) {
            res.send(html + "<p style='color: red; font-size: 25px'>NIKT NIE JEST ZALOGOWANY, ALE MOŻESZ ZROBIĆ TO TERAZ</p>")
        })
    }
    
    
}

exports.login_get = function (req,res)
{
    stan=req.stan;
    res.render("logins",{stan: stan});
}
exports.login = (req, res, next) => {
    stan=req.stan;
    var nazwisko = req.body.nazwisko
    var password = req.body.haslo
   
    User.findOne({nazwisko}) // login jako nazwisko
    .then(user => {
        if(user) {
            //Porównujemy hasło podane w logowaniu z hasłem użytkownika
            bcrypt.compare(password, user.haslo, function (err,result){

                if(err){

            
                    res.json({
                        error: err
                    })
                }



                if (result) {
                    //tworzymy token, gdzie 'KodSzyfrujący" to tajny kod na podstawie którego
                    //generowany jest hash, będzie on potrzebny do sprawdzenia tokena później
                    let token = jwt.sign({_id: user.id}, 'kodSzyfrujacy', {expiresIn: '1h'})
                    
                   

                    //req.localStorage = token;

                   
                    if(typeof localStorage === "undefined" || localStorage === null)
                    {
                        var LocalStorage = require('node-localstorage').LocalStorage;
                        localStorage = new LocalStorage('./scratch');

                        
                    }
                    localStorage.setItem('token', token);
                    
                    console.log(localStorage.getItem('token'));
                    console.log(req.headers.authorization);


                    // stan = true;
                    // res.render('home' ,{stan: stan});
                   
                        
                   res.redirect('/')
                        
                   
                   

                    
                }else {
                    // res.json({message: 'Złe hasło'})
                    res.render('logins', {stan: stan}, function (err, html){
                        res.send(html + "<p style='color: red;'>Podane hasło jest nieprawidłowe</p>")
                    } ) 
                    
                
                }







            })
           
        
    }else {
        // res.json({
        //     message: 'No user found!'
        // })
        //res.render("logins")
        
        res.render('logins', {stan: stan}, function (err, html){
            res.send(html + "<p style='color: red;'>Nie ma takiego użytkownika</p>")
        } ) 
    }

    })


}

exports.register_get = function(req,res) 
{
    stan=req.stan;
    res.render('register', {stan: stan});
    console.log("Pojawiam formularz rejestracji")
}

exports.register_post = (req, res, next) => {
    stan=req.stan;
    console.log("wywołuje post register user")
    console.log(req.body.haslo + " TO JEST HASLO ")
    //Tutaj pobieram password z body daltego pole musi się nazywać password
    bcrypt.hash(req.body.haslo, 10, function(err, PasswordHash){
        if(err){
            console.log(req.body.haslo + " Po haszu " + PasswordHash);
            res.json({error: 'bląd funkcji haszującej'})
        }
       // Tworzenie obiektu urzytkownika
      //  w formularzy pole musi być nazwane password ale wszędzie jest to haslo 
        let user = new User({
            imie: req.body.imie,
            nazwisko: req.body.nazwisko,
            haslo: PasswordHash,
            email: req.body.email,
            
            
        })
        console.log("TuTAj sprawdzanie " + PasswordHash);
        if(req.body.nazwisko != "" && req.body.haslo!="")
        {
        user.save().then(() => {
            res.redirect("/")
        }).catch(()=>{
            console.log(req.body.imie + "TOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo");
            res.render('register',{stan: stan}, function (err, html){
                res.send(html + "<p style='color: red;'>Nie poprawne dane</p>")
            } ) 
        })
        }else {

            res.render('register',{stan: stan}, function (err, html){
                res.send(html + "<p style='color: red;'>Nie poprawne dane</p>")
            } )

        }
     })
}







exports.index = function(req,res)
{
    stan=req.stan;
    console.log("wywołanie get index user")
        User.find().then(function(users){
            res.render('indexUser', {title: "Users", items: users, stan: stan})
        })
};

exports.user_list = function(req,res) {
    let userslist

    const getUsers = async () => {
        try{
            userslist = await User.find()
            console.log(userslist)
            res.send(userslist)
        }catch(err){
            console.log(err)
        }
    }
    getUsers()
};

exports.user_create_get = function(req,res)
{
    stan=req.stan;
    res.render('createUser', {stan:stan});
}

exports.user_create_post = function(req,res) {

    
    var user = new User({
        imie: req.body.imie, nazwisko: req.body.nazwisko, login: req.body.login, haslo: req.body.haslo, email: req.body.email
    });
    
    user.save().then(()=>{
        console.log("user jest dodany")
        res.send('add new user');
    }).catch(err => {
        console.log(err)
    })
};

console.log('kontroler user gotowy')


