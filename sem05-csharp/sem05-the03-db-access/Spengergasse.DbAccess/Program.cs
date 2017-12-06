using Newtonsoft.Json;
using System.Data.Entity.Core.Metadata.Edm;
using System.Data.Entity.Core.Objects;
using System.Data.Entity.Infrastructure;
using System.Linq;

namespace Spengergasse.DbAccess {
  class Program {
    static void Main(string[] args) {
      Schule2000Entities db = new Schule2000Entities();

      ObjectContext objContext = ((IObjectContextAdapter) db).ObjectContext;

      var erg = objContext.MetadataWorkspace.GetItems<EntityType>(DataSpace.CSpace)
        .Select(x => x.Name);

      System.Console.WriteLine(JsonConvert.SerializeObject(erg));

      System.Console.ReadKey();
    }
  }
}
