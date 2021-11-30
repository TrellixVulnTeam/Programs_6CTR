const mongoose = require('mongoose')

const PolubieniaSchema = new mongoose.Schema({
    
    id_usera: {
        type: String,
    
    },
    id_zdjecia: {
        type: String,
    },
   
    


})
const Polubienia = mongoose.model('Polubienia', PolubieniaSchema) 
console.log("Model polubienia gotowy")

module.exports = Polubienia