var Tag = require('../models/tag');

//zdefiniowanie pustych funkcji
exports.index = function(req, res) {
    console.log("wywołanie get index tag")

    Tag.find().then(function(tags){
        res.render('indexTag', {title: "Tags", items: tags})
    });


    
};

exports.tag_list = function(req, res) {

    let taglist

    const getTags = async () => {
        try{

            taglist = await Tag.find()
            console.log(taglist)
            res.send(taglist);


        }catch(err){
            console.log(err)
        }
    }

    getTags()

};

exports.tag_create_get = function(req,res)
{
    res.render('createTag')
}

exports.tag_create_post = function(req, res, next) {
    
    var tag = new Tag({
        tekst: req.body.tag,
    });


    tag.save().then(()=>{
        console.log(" Tag został dodany")
        res.send('add new tag');
    }).catch(err =>{
        console.log(err)
    })



};

console.log("kontroler tag gotowy")