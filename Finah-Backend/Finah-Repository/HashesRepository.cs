using System;
using Finah_DomainClasses;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Finah_Repository
{
    public class HashesRepository
    {
        public List<hashes> GetHashes()
        {
            try
            {
                var context = new db_projectEntities();
                var hashesList = context.hashes.ToList();
                return hashesList;
            }
            catch (Exception)
            {
                return null;
            }

        }

        public hashes GetHashesById(int id)
        {
            try
            {
                var context = new db_projectEntities();
                var hashesWithId = context.hashes.First(h => h.id == id);
                return hashesWithId;
            }
            catch (Exception)
            {
                return null;
            }

        }

        public hashes AddHashes(hashes newHashes)
        {
            try
            {
                var context = new db_projectEntities();
                if (newHashes == null)
                {
                    throw new ArgumentNullException("newHashes");
                }
                context.hashes.Add(newHashes);
                context.SaveChanges();
                return newHashes;
            }
            catch (Exception)
            {
                return null;
            }

        }

        public Boolean UpdateHashes(int id, hashes hashes)
        {
            try
            {
                if (hashes == null || id == null)
                {
                    return false;
                }
                else
                {
                    var context = new db_projectEntities();
                    var updatedHashes = context.hashes.First(h => h.id == id);
                    updatedHashes.hash = hashes.hash;
                    updatedHashes.status = hashes.status;
                    updatedHashes.user = hashes.user;
                    updatedHashes.date = hashes.date;
                    context.SaveChanges();
                    return true;
                }
            }
            catch (Exception)
            {
                return false;
            }

        }

        public Boolean Deletehashes(int id)
        {
            try
            {
                var context = new db_projectEntities();
                var hash = context.hashes.First(h => h.id == id);
                context.hashes.Remove(hash);
                context.SaveChanges();
                return true;
            }
            catch (Exception)
            {
                return false;
            }

        }
    }
}
