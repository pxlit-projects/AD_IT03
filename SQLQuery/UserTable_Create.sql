BEGIN
	IF EXISTS (
		SELECT * FROM sys.objects 
		WHERE object_id = OBJECT_ID(N'[dbo].[User]') 
		AND type in (N'U')) 
		DROP TABLE [dbo].[User]
	
CREATE TABLE [dbo].[User](
    [id] [int] NOT NULL IDENTITY(1,1),
    [firstname] [nvarchar](50) NOT NULL,
    [lastname] [nvarchar](50) NOT NULL,
	[login] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NOT NULL,
	[email] [nvarchar](100) NOT NULL,
	[type] [int] NOT NULL
    CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED   
(
[id] ASC
)WITH (IGNORE_DUP_KEY = OFF)
) 
END;
GO