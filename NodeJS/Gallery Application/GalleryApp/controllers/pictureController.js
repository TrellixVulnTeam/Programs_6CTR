var Picture = require('../models/picture');
const Komentarz = require('../models/komentarz');
const Polubienia = require('../models/polubienia');

var formidable = require("formidable");
var fs = require("fs");
var stan;
//zdefiniowanie pustych funkcji
exports.index = function(req, res) {
    console.log("wywołanie get index picture")
   
    stan=req.stan;
    Picture.find().then(function(pictures){
        res.render('index', {title: "Gallery", items: pictures, stan: stan});

    });
};

exports.picture_list = function(req, res) {

    let picturesList

    const getPictures = async () => {
        try{

            picturesList = await Picture.find()
            console.log(picturesList)
            //res.send(picturesList);
            res.sendFile(__dirname + '/index.html')

        }catch(err){
            console.log(err)
        }
    }

    getPictures()

};
exports.picture_info = function (req,res)
{   
    stan=req.stan;
    var id = req.params.id
    Picture.findOne({_id: id}).then(function (pictures) {
        Komentarz.find({id_zdjecia: id}).then(function(comments){
            res.render('show',{item: pictures, commentsList: comments, stan: stan});
        });
        // console.log(picture)
       
    });
    console.log(req.params)
    console.log("Sprawdzam co to !!!!!!!!!!!!!!!!!!")
}

exports.picture_create_get = function (req, res){
    stan=req.stan;
    res.render('create', {stan: stan});
};




exports.picture_create_post = function(req, res, next) {



    var picture = new Picture({
        nazwa: req.body.nazwa,
        rozmiar: req.body.rozmiar,
    });
    
    picture.save().then(()=>{
        console.log("Dodaje zdjęcie")
        res.render('show', {item: req.body, path: "../images/"});
    }).catch(err =>{
        console.log(err)
    })



};

// Funkcja dodająca nowe zdjęcie
exports.picture_upload_post = function(req,res)
{
    var formData = new formidable.IncomingForm();
    formData.uploadDir='public/images'; // Doradzili mi na necie
    formData.parse(req, function (error, fields, files){
        
        var oldpath = files.file.path;
        var extension = files.file.name.substr(files.file.name.lastIndexOf("."));
        
        var newPath = "./public/images/" + fields.fileName + extension;

        console.log("FILES FILE NAME" + files.file.path)
        fs.rename(oldpath, newPath, function (errorRename){

             if(errorRename) throw errorRename;

            console.log("Przenosze plik do folderu")

            
            var rozm = files.file.size;
            console.log("Czy to jest nazwa tego pliku " + fields.fileName)
            
            if(rozm >= 10000)
            {
                var picture = new Picture({
                    nazwa: fields.fileName + extension,
                    rozmiar: 10000,
                });
            }else
            {
                var picture = new Picture({
                    nazwa: fields.fileName + extension,    
                    rozmiar: rozm,
                });
            }
        
            
            picture.save().then(()=>{

                 res.redirect('../pictures')
            }).catch(err =>{
                console.log(err)
            })
        




        });
    })
}




exports.picture_delete_post = function(req,res)
{
    const id = req.body.id
    stan=req.stan;
    console.log(id + " To zostanie usunięte" )
    Picture.findByIdAndDelete(id, 
        function(err,docs){
            if(err){
                console.log(err)
            }
            else{
                console.log("usuwam")
                res.render('index', {stan: stan})
            }
        });
}






// W funkcji like trzeba najpierw wyszukać zdjęcie żeby sprawdzić ile ma like i dodać do tej liczby 1 i spowrotem zapisać, ponad to sprawdzić id uzytkownika ktory obecnie jest zalogowany 
// exports.like = function (req,res)
// {
//     let picture = {}
//     picture.likes = 
//     const id = req.body.id
//     console.log("WCHOIDZE DO FUNKCJI LIKE" + req.body.id);
//     Picture.findByIdAndUpdate(id, picture, function (err, docs){
//         if(err){
//             console.log(err)
//         }else{
//             res.redirect('/pictures')
//         }
//     })
//     // const id = req.params.id
//     // console.log("To jest ID które chce polajkować " + id)
//    // Picture.findByIdAndUpdate()

// };

exports.like = function (req,res)
{

    const id = req.body.id
    
    Polubienia.findOne( {id_usera: req.user._id, id_zdjecia: id}, function (err, polubione ) {

        if(polubione)
        {
            console.log("Like został już oddany")

            Polubienia.deleteOne({id_zdjecia: id, id_usera:req.user._id}, 
                function(err,docs){
                    if(err){
                        console.log(err)
                    }
                    else{
                        console.log("usuwam");
                     
                        
                    }
                });



                Picture.updateOne({_id:id},{
        
                    $inc: { likes: -1 } 
                
                }).then(()=>{
                    res.redirect('back');
                    console.log("WSZYSTKO SIE UDALO");
                })




        }else{

            Picture.updateOne({_id:id},{
        
                $inc: { likes: 1 } 
            
            }).then(()=>{
                res.redirect('back');
                console.log("WSZYSTKO SIE UDALO");
            })

            
            var like = new Polubienia({
                id_zdjecia: id, id_usera: req.user._id
            });
            
            like.save().then(()=>{
                console.log("like jest dodany")
                res.send('add new like');
            }).catch(err => {
                console.log(err)
            })

        }
    
        
    

    })

    
    
    
    
    

    // var polubieniaNowe = Polubienia.findOne();

    // console.log("POROSTY KONSOLOWY LOG " + polubieniaNowe.id_zdjecia)

    
    //Polubienia.findOne({id_zdjecia: id})// Tutaj szukam po id zdjecia i id urzytkownika) 
    //jeśli jest to powiadom użytkownika że już like dany 
    // jeżeli nie ma takich to dodaj like i rekord do polubień (id_usera, id_zdjecia)




    
  
    
}



exports.picture_update_post = function (req,res) 
{



    let picture = {}
    
    const id = req.params.id
  
    var formData = new formidable.IncomingForm();
    formData.uploadDir='public/images'; 
    formData.parse(req, function (error, fields, files){
        
        var oldpath = files.file.path;
        var extension = files.file.name.substr(files.file.name.lastIndexOf("."));
        picture.nazwa = fields.fileName + extension
        var newPath = "./public/images/" + fields.fileName + extension;
 
        console.log("FILES FILE NAME" + files.file.path)
        fs.rename(oldpath, newPath, function (errorRename){

             if(errorRename) throw errorRename;

            console.log("Przenosze plik do folderu")

            
            var rozm = files.file.size;
            console.log("Czy to jest nazwa tego pliku " + req.body.nazwa)
            
            if(rozm >= 10000)
            {
                picture.rozmiar = 10000;
                Picture.findByIdAndUpdate(id, picture, function (err, docs){
                    if(err){
                        console.log(err)
                    }
                    else{
                        res.redirect('/pictures')
                    }
                });
                    // nazwa: req.body.nazwa + extension,
                    
                
            }else
            {
                picture.rozmiar = rozm;
                Picture.findByIdAndUpdate(id, picture, function (err, docs){
                    if(err){
                        console.log(err)
                    }
                    else{
                        res.redirect('/pictures')
                    }
                });
               
            }
        
            
            // picture.save().then(()=>{
                
            //      res.redirect('../pictures')
            // }).catch(err =>{
            //     console.log(err)
            // })
        




        });
    })
};


exports.picture_update_get = function (req,res) {
    stan=req.stan;
    const id = req.params.id
    let data;
    Picture.findOne({_id:id}).then(function (picture){
        res.render('edit', {item: picture, id:id, stan: stan});
    });
};


// Dodaje nowy kod by uploadować plik







console.log("kontroler gotowy")