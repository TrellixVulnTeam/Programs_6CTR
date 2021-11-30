from . models import Computer
from rest_framework import serializers
class ComputerSerializer ( serializers . HyperlinkedModelSerializer ):
    class Meta :
        model = User
        fields = [ 'manufacturer','modelName' , 'releaseDate']