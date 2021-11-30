var Sesja = require('../models/sesja');

//zdefiniowanie pustych funkcji
exports.index = function(req, res) {
    console.log("wywołanie get index session")
    Sesja.find().then(function(sessions){
        res.render('indexSession', {title: "Sessions", items: sessions})
    });
};

exports.session_list = function(req, res) {

    let sessionlist

    const getSessions = async () => {
        try{

            sessionlist = await Sesja.find()
            console.log(sessionlist)
            res.send(sessionlist);


        }catch(err){
            console.log(err)
        }
    }

    getSessions()

};

exports.session_create_get = function(req,res)
{
    res.render("createSession")
}

exports.session_create_post = function(req, res) {
    
    var session = new Sesja({
        token: req.body.token,
        waznosc: req.body.waznosc,
    });

    session.save().then(()=>{
        console.log("sesja dodana ")
        res.send('add new session');
    }).catch(err =>{
        console.log(err)
    })



};

console.log("kontroler session gotowy")