using System;
using System.Collections.Generic;
using System.Linq;
using LinqInAction.LinqBooks.Common;

namespace Spengergasse.LinqIntroduction {
  class Program {
    static void Main(string[] args) {
      ObjectDumper.Write(
        from b in SampleData.Books
        where b.Price > 0
        orderby b.Price descending
        select new { b.Isbn, b.Title, Preis2 = b.Price }
      );

      PrintHeading("PageCount > 200");

      ObjectDumper.Write(
        from book in SampleData.Books
        where book.PageCount > 200
        select new { book.Isbn, book.Title, book.Price, book.PageCount }
      );

      PrintHeading("Published before August 2007");

      ObjectDumper.Write(
        from books in SampleData.Books
        where books.PublicationDate < new DateTime(2007, 8, 1)
        orderby books.Title
        select new { books.PublicationDate, books.Title, books.Isbn }
      );

      PrintHeading("Books published by FunBooks");

      ObjectDumper.Write(
        from books in SampleData.Books
        where books.Publisher.Name == "FunBooks"
        orderby books.Title
        select new { books.Isbn, books.Title, books.Publisher.Name }
      );

      PrintHeading("Subject 'Software development'");

      ObjectDumper.Write(
        from books in SampleData.Books
        where books.Subject.Name == "Software development"
        orderby books.Title
        select new { books.Title, books.Subject.Description }
      );

      PrintHeading("Publishers with descriptions");

      ObjectDumper.Write((
        from book in SampleData.Books
        where book.Subject.Name == "Software development"
        orderby book.Title
        select new {
          book.Publisher.Name,
          book.Publisher.Website,
          book.Subject.Description
        }
      ).Distinct());

      PrintHeading("Books");

      ObjectDumper.Write(
        from book in SampleData.Books
        where book.Subject.Name == "Software development"
        orderby book.Title
        select new {
          book.Title,
          book.PageCount,
          Authors = book.Authors
            .Select(author => author.FirstName + " " + author.LastName)
            .Aggregate((pre, cur) => pre + ", " + cur)
        }
      );

      PrintHeading("Books by Octavio Prince");

      ObjectDumper.Write(
        from book in SampleData.Books
        where book.Authors.Any(author =>
          author.FirstName == "Octavio"
          && author.LastName == "Prince"
        )
        select new { book.Title, book.PageCount }
      );

      PrintHeading("Books by Octavio Prince as main author");

      ObjectDumper.Write(
        from book in SampleData.Books 
        let first = book.Authors.First()
        where first.FirstName == "Octavio"
          && first.LastName == "Prince"
        select new { book.Title, book.PageCount }
      );

      PrintHeading("Publisher with book counts");

      ObjectDumper.Write(
        from book in SampleData.Books
        group book by book.Publisher into g
        select new { g.Key.Name, Count = g.Count() }
      );

      PrintHeading("Books with author counts");

      ObjectDumper.Write(
        from book in SampleData.Books
        select new {
          Publisher = book.Publisher.Name,
          book.Title,
          Authors = book.Authors.Count()
        }
      );

      PrintHeading("Subjects with books");

      var subjects =
        from book in SampleData.Books
        group book by book.Subject;

      foreach (var subjectGroup in subjects) {
        System.Console.WriteLine(subjectGroup.Key.Name);
        ObjectDumper.Write(subjectGroup.Select(
          book => new { book.Isbn, book.Title }
        ));
        System.Console.WriteLine();
      }

      System.Console.WriteLine();
    }

    static void PrintHeading(string heading) {
      System.Console.WriteLine("\n{0}\n---\n", heading);
    }
  }
}
