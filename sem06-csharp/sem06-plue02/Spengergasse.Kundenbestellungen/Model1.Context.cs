﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Spengergasse.Kundenbestellungen
{
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Infrastructure;
    
    public partial class KaiserNordwindEntities : DbContext
    {
        public KaiserNordwindEntities()
            : base("name=KaiserNordwindEntities")
        {
        }
    
        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            throw new UnintentionalCodeFirstException();
        }
    
        public virtual DbSet<Artikel> Artikels { get; set; }
        public virtual DbSet<Bestelldetail> Bestelldetails { get; set; }
        public virtual DbSet<Bestellungen> Bestellungens { get; set; }
        public virtual DbSet<Kategorien> Kategoriens { get; set; }
        public virtual DbSet<Kunden> Kundens { get; set; }
        public virtual DbSet<Lieferanten> Lieferantens { get; set; }
        public virtual DbSet<Personal> Personals { get; set; }
        public virtual DbSet<sysdiagram> sysdiagrams { get; set; }
        public virtual DbSet<Versandfirman> Versandfirmen { get; set; }
    }
}
