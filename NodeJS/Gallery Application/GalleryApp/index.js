const express = require('express')
const port = 8000
const bodyParser = require('body-parser')
//Do testu na plikach

var isLogged = require('../Zadanie12/middleware/isLogged');
var formidable = require("formidable");
var fs = require("fs");

const app = express()
const picturesRoutes = require('./routes/pictures.js') // musimy dodać połączenie z plikiem picture.js
const userRoutes = require('./routes/users.js')
const adminRoutes = require('./routes/administrators.js')
const commentRoutes = require('./routes/comments.js')
const miniatureRoutes = require('./routes/miniatures.js')
const sessionRoutes = require('./routes/sessions.js')
const tagRoutes = require('./routes/tags.js')
const mongoose = require('./db/mongoose')
const Picture = require('./models/picture')
const User = require('./models/user')
const Administrator = require('./models/administrator')
const Komentarz = require('./models/komentarz')
const Miniatura = require('./models/miniatura')
const Sesja = require('./models/sesja')
const Tag = require('./models/tag')
const Polubienia = require('./models/polubienia')

// moduły potrzebne do generowania widoków
const hbs = require('express-handlebars');
var path = require('path');

// aby nasze zdjęcia były widoczne należy dodać parametr
app.use(express.static(path.join(__dirname, 'public')));


  app.use(bodyParser.urlencoded({
    extended: true
  }));

app.use(bodyParser.json());

app.use('/pictures', picturesRoutes) // dodajemy ścieżke do naszej aplikacji
app.use('/users', userRoutes);
app.use('/admins', adminRoutes);
app.use('/comments', commentRoutes);
app.use('/miniatures', miniatureRoutes);
app.use('/sessions', sessionRoutes);
app.use('/tags', tagRoutes);

// ustawienie silnika dla layoutu najdującego się w folderze views/layouts o nazwie layout.hbs
app.engine('hbs', hbs({extname: 'hbs', defaultLayout: 'layout', layoutsDir: __dirname + '/views/layouts/',
//dodanie opcji runtime 
runtimeOptions: {
  allowProtoPropertiesByDefault: true,
  allowProtoMethodsByDefault: true
}}));








app.set('view engine', 'hbs')


var stan;

app.get('/', function(req, res){
  isLogged;
  stan = req.stan;
  res.render('home', {stan: stan});

});





//Standardowe dodanie zdjęcia
//const picture = new Picture({nazwa: 'foto_02', sciezka: './images', rozmiar:'2345'})
//picture.save().then(()=>{console.log(picture)}).catch(err=>{console.log(err)})


//wyszukanie zdjęcia
// Picture.find({}).then((pictures)=>{
//   console.log(pictures)
// })

// wyszukiwanie asynchroniczne 
// const getPictures = async () => {
//       try{
//         const pictures = await Picture.find()
//         console.log(pictures)
//       }catch(error){
//         console.log(error)
//       }
// }
















//Wyszukiwanie z filtrem

// Picture.find({

//   rozmiar: {$gt: 2343}

// }).then((pictures)=>{
//   console.log(pictures)
// })



//Edytowanie 
// Picture.updateOne(
//   {nazwa: 'foto_02'},
//   {$set: {
//     rozmiar: 2222
//   }}
// ).then((pictures)=>{
//   console.log(pictures)
// })


//dodawanie 

// const addPicture = async (pictureData) => {
//   try{
//       const picture = new Picture(pictureData)
//       picture.save()
//       console.log(picture)

//   }catch(err){
//     console.log(err)
//   }
// }


// addPicture({
//   nazwa: "foto_03",
//   rozmiar: 4567
// })


// const addUser = async (userData) => {
//   try{
//       const user = new User(userData)
//       user.save()
//       console.log(user)

//   }catch(err){
//     console.log(err)
//   }
// }


// addPicture({
//   imie: "Jan",
//   nazwisko: "Kowalski",
//   login: "Jan23",
//   Haslo: "haslo",
// })




// getPictures(); // wywołanie funkcji wypisywania

app.listen(port);







