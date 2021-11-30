var Miniatura = require('../models/miniatura');

//zdefiniowanie pustych funkcji
exports.index = function(req, res) {
    console.log("wywołanie get index miniature")
    Miniatura.find().then(function(miniatures){
        res.render('indexMiniature', {title: "Miniatures", items: miniatures})
    });
};

exports.miniature_list = function(req, res) {

    let miniatureList

    const getMiniatures = async () => {
        try{

            miniatureList = await Miniatura.find()
            console.log(miniatureList)
            res.send(miniatureList);


        }catch(err){
            console.log(err)
        }
    }

    getMiniatures()

};


exports.miniature_create_get = function(req,res)
{
    res.render("createMiniature")
}

exports.miniature_create_post = function(req, res) {
    
    var miniature = new Miniatura({
        typ: req.body.typ,
    });

    miniature.save().then(()=>{
        console.log("miniatura dodana")
        res.send('add new miniatura');
    }).catch(err =>{
        console.log(err)
    })



};

console.log("kontroler miniatura gotowy")