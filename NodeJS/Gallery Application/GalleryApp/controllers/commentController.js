var Komentarz = require('../models/komentarz');
const User = require('../models/user');

//zdefiniowanie pustych funkcji


exports.add = function (req, res) {



    User.findOne( {_id:req.user._id}, function (err, user) {

        const comments = new Komentarz({

            user: user.imie + " " + user.nazwisko,
            tresc: req.body.tresc,
            id_zdjecia: req.body.id_zdjecia
    
        });
    
        comments.save().then(() => {
            res.redirect('/pictures/' + comments.id_zdjecia);
    
        }).catch(err => {
            console.log(err)
        })
    

    })


//    var uzytkownik = User.findById({_id: req.user._id}).then(()=>{

    

    
//    })

   


};

exports.index = function(req, res) {
    console.log("wywołanie get index comment")
    Komentarz.find().then(function(comments){
        res.render('indexComment', {title: "Comments", items: comments})

    });
};

exports.comment_list = function(req, res) {

    let commentlist

    const getComments = async () => {
        try{

            commentlist = await Komentarz.find()
            console.log(commentlist)
            res.sendFile(__dirname + '/index.html');
            

        }catch(err){
            console.log(err)
        }
    }

    getComments()

};

exports.comment_create_get=function(req,res){
    res.render('createComment')
}

exports.comment_create_post = function(req, res, next) {
    

    var comment = new Komentarz({
        tresc: req.body.tresc,
    });

    if(req.body.tresc !=""){


        comment.save().then(()=>{
            console.log("Dodaje zdjęcie")
            res.send('add new comment');
        }).catch(err =>{
            console.log(err)
        })

    }else 
    { res.render('createComment', function (err, html){
        res.send(html + "<p style='color: red;'>Komentarz nie może być pusty</p>")
    } )}


    



};

console.log("kontroler comment gotowy")