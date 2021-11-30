from django import forms

from .models import Samochod


class PostForm(forms.Form):
    opis = forms.CharField(max_length=250, required=False)

class DataForm(forms.Form):
    data = forms.DateField()