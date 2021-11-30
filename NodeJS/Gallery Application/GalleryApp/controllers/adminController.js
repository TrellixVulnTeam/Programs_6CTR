var Administrator = require('../models/administrator');

//zdefiniowanie pustych funkcji
exports.index = function(req, res) {
    console.log("wywołanie get index admin")
     Administrator.find().then(function(admins){
         res.render('indexAdmin', {title: "Admins", items: admins})
     })

};

exports.admin_list = function(req, res) {

    let adminslist

    const getAdmins = async () => {
        try{

            adminslist = await Administrator.find()
            console.log(adminslist)
            res.send(adminslist);


        }catch(err){
            console.log(err)
        }
    }

    getAdmins()

};
exports.admin_create_get = function(req,res)
{
    res.render('createAdmin')
} 



exports.admin_create_post = function(req, res) {
    
    var admin = new Administrator({
        Prawa: req.body.prawa
    })

    admin.save().then(()=>{
        console.log(admin)
        res.send('add new admin');
    }).catch(err =>{
        console.log(err)
    })



};

console.log("kontroler admin gotowy")