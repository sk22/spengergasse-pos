# Console Weather App

## Building and Running

```
cd Spengergasse.ConsoleWeatherApp
dotnet build -c release
dotnet .\bin\Release\netcoreapp2.0\Spengergasse.ConsoleWeatherApp.dll
```

## Framework

The framework required to build this app is `dotnetcore2.0`.

## Team

1. Samuel Kaiser <kai17521@spengergasse.at>

## .NET Core Projects

* `Spengergasse.Weather` Library for interacting with an online weather API
  - Communicating with the API
  - Parsing the loaded JSON or XML data
  - Exporting classes and methods to use the fetched data

* `Spengergasse.ConsoleWeatherApp` Console program for displaying the weather
  - Inputting demanded information (place, type of data)
  - Displaying the information
  - Persisting the places and provide a picker of recents
  - Convenient controls
