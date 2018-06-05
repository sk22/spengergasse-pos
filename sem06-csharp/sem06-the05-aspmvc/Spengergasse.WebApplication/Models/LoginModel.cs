using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace Spengergasse.WebApplication.Models {
  public class LoginModel {
    [Display (Name = "User name")]
    [Required]
    public string UserName { get; set; }

    [Required]
    [DataType(DataType.Password)]
    public string Password { get; set; }

    [Display (Name = "Remember me")]
    public bool RememberMe { get; set; }
  }
}