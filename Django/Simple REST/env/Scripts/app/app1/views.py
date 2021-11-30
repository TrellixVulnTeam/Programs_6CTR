from django.shortcuts import render
from rest_framework import viewsets
from . serializers import ComputerSerializer
class ComputerViewSet ( viewsets . ModelViewSet ):
    queryset = Computer . objects .all ()
    serializer_class = ComputerSerializer

def computers ( request ):
    computers = Computer . objects .all ()
    return render ( request ,'computers . html',{ ' computers ': computers })
# Create your views here.
def list ( self , request ):
    queryset = Computer . objects .all ()
    serializer = ComputerSerializer ( queryset , many = True )
    return Response ( serializer . data )
