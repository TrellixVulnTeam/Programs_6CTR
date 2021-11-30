const mongoose = require('mongoose')

const UserSchema = new mongoose.Schema({

    imie: {
        type: String,
        
    },
    nazwisko: {
        type: String,
        
    },
    haslo: {
        type: String,
        required: true,
    },
    email: {
        type: String,
        
    },

})

const User = mongoose.model('User', UserSchema)

console.log("model user gotowy")

module.exports = User