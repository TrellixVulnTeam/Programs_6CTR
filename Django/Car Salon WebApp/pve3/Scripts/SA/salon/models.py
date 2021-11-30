from django.db import models
from django.contrib.auth.models import User
import uuid

#
# class Pizza(models.Model):
#     LARGE = 'L'
#     MEDIUM = 'M'
#     SMALL = 'S'
#     ROZMIARY = (
#         (LARGE, 'duża'),
#         (MEDIUM, 'średnia'),
#         (SMALL, 'mała'),
#     )
#     nazwa = models.CharField(verbose_name='Pizza', max_length=30)
#     opis = models.TextField(blank=True, help_text='Opis Pizzy')
#     rozmiar = models.CharField(max_length=1, choices = ROZMIARY, default=LARGE)
#     cena = models.DecimalField(max_digits=5, decimal_places = 2)
#     data = models.DateField('dodano', auto_now_add=True)
#
#
# class Skladnik(models.Model):
#     pizza = models.ForeignKey(Pizza,
#                               on_delete=models.CASCADE,
#                               related_name='skladniki')
#     nazwa = models.CharField(verbose_name = u"składniki", max_length = 30)
#     jarski = models.BooleanField(
#         default=False,
#         verbose_name = u"jarski?",
#         help_text=u"Zaznacz, jeżeli sładnik jest odpowiedni dla" u" wegetarian")


    #class Meta:
       # verbose_name = u'samochód'
      #  verbose_name_plural = u'samochody'
      #  ordering = ['marka']
   # def __str__(self):
      #  return self.opis #wyświetlnie opisu

class wiadomosci(models.Model):
    nick = models.CharField(verbose_name="nick", max_length=30, null=False)
    wiadomosc = models.TextField(blank= False)
    data = models.DateTimeField(auto_now_add=True, null=True)
class sell(models.Model):
   kupujacy = models.CharField(verbose_name='Kupujacy', max_length=30, null=False)
   marka = models.CharField(verbose_name='Nazwa marki', max_length=30)
   model = models.CharField(verbose_name='Nazwa modelu', max_length=20)
   cena = models.FloatField(name="Cena", default=0.0)
   data = models.DateTimeField(auto_now_add=True)
   id = models.AutoField(primary_key=True)   
# Create your models here.
class Samochod(models.Model):
    marka = models.CharField(verbose_name='Nazwa marki', max_length=30)
    model = models.CharField(verbose_name='Nazwa modelu', max_length=20)
    data_produkcji = models.DateTimeField(verbose_name='data produkcji')
    opis = models.TextField(blank=True)
    id = models.IntegerField(primary_key = True, null=False, default=0)
    cena = models.FloatField(name="cena", default=0.0)
    link = models.CharField(verbose_name='zdjecie', max_length=200, null=True)
class proba(models.Model):
   data = models.DateField(verbose_name='Data')
   uczestnik = models.CharField(verbose_name='Uczestnik', max_length=40)
   auto = models.CharField(verbose_name='auto', max_length=100, null=True )