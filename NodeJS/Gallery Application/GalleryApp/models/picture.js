const mongoose = require('mongoose')

const PictureSchema = new mongoose.Schema({
    
    tytul: {
        type: String,
    
    },
    opis: {
        type: String,
    },
    data: 
    {
        type: Date,
        default: '2020-01-01',
    },
    nazwa: {
        type:String,
        required: true,
    },
    sciezka: {
        type: String,
        default: './images'
    },
    rozmiar: {
        type: Number,
        max: 10000
    },
    likes: {
        type: Number,
        default: 0,
    }
    


})
const Picture = mongoose.model('Picture', PictureSchema) 
console.log("Model picture gotowy")

module.exports = Picture